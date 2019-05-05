package com.scheduler.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.jobs.CronJob;
import com.scheduler.services.JobService;

@RestController
@RequestMapping("/scheduler/")
public class JobController {
	
	@Autowired
	@Lazy
	JobService jobService;

	@RequestMapping("schedule")	
	public ServerResponse schedule() throws ParseException{
		
		String jobName = "backupJob2";
		String pattern = "yyyy/MM/dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date jobScheduleTime = simpleDateFormat.parse("2019/05/04 19:25");
		
		String cronExpression = "0 0/1 * 1/1 * ? *";
		
		
		System.out.println("JobController.schedule()");

		//Job Name is mandatory
		if(jobName == null || jobName.trim().equals("")){
			return getServerResponse(ServerResponseCode.JOB_NAME_NOT_PRESENT, false);
		}

		//Check if job Name is unique;
		if(!jobService.isJobWithNamePresent(jobName)){

			//Cron Trigger
			boolean status = jobService.scheduleCronJob(jobName, CronJob.class, jobScheduleTime, cronExpression);
			if(status){
				return getServerResponse(ServerResponseCode.SUCCESS, jobService.getAllJobs());
			}else{
				return getServerResponse(ServerResponseCode.ERROR, false);
			}		
		}else{
			return getServerResponse(ServerResponseCode.JOB_WITH_SAME_NAME_EXIST, false);
		}
	}
	
	public ServerResponse getServerResponse(int responseCode, Object data){
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse; 
	}
}
