package com.yukti.jobscheduler.controller.job;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yukti.jobscheduler.controller.ApiError;
import com.yukti.jobscheduler.controller.ServerResponse;
import com.yukti.jobscheduler.controller.ServerResponseCode;
import com.yukti.jobscheduler.controller.ServerResponseHandler;
import com.yukti.jobscheduler.jobs.CronJob;
import com.yukti.jobscheduler.model.Job;
import com.yukti.jobscheduler.services.job.JobService;


@RestController
@RequestMapping("/scheduler/")
public class JobController {
	
	@Autowired
	@Lazy
	JobService jobService;

	/**
	 * @param jobName
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "schedule")	
	public ServerResponse schedule(@RequestBody Job  job) throws ParseException{
		
		String jobName = job.getJobName();
		String pattern = "yyyy/MM/dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date jobScheduleTime = simpleDateFormat.parse("2019/05/04 19:25");
		
		String cronExpression = "0 0/1 * 1/1 * ? *";
		
		ApiError apiError = new ApiError();
		
		apiError.setStatusCode(HttpStatus.CONFLICT.value());
		
		System.out.println("JobController.schedule()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			
			return ServerResponseHandler.getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, HttpStatus.NO_CONTENT, "Job name is not specified", null);
			
		}

		//Check if job Name is unique;
		if(!jobService.isJobWithNamePresent(jobName)){

			//Cron Trigger
			boolean status = jobService.scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);
			if(status){
				return ServerResponseHandler.getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
			}else{
				return ServerResponseHandler.getServerResponse(ServerResponseCode.ERROR, false);
			}		
		}else{
			return ServerResponseHandler.getServerResponse(ServerResponseCode.JOB_WITH_SAME_NAME_EXIST, false);
		}
	}
	
	
}
