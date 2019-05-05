package com.yukti.jobscheduler.services.job;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.yukti.jobscheduler.util.JobUtil;




/**
 * @author Bishwadeep Roy
 *
 */
@Service
public class JobServiceImpl implements JobService{

	@Autowired
	@Lazy
	SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private ApplicationContext context;

	/**
	 * Schedule a job by jobName at given date.
	 */
	@Override
	public boolean scheduleOneTimeJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date) {

		return false;
	}
	
	/**
	 * Schedule a job by jobName at given date.
	 */
	@Override
	public boolean scheduleCronJob(String jobName, Class<? extends QuartzJobBean> jobClass, Date date, String cronExpression) {
		System.out.println("Request received to scheduleJob");

		String jobKey = jobName;
		String groupKey = "YuktiGroup";	
		String triggerKey = jobName;		

		JobDetail jobDetail = JobUtil.createJob(jobClass, false, context, jobKey, groupKey);

		System.out.println("creating trigger for key :"+jobKey + " at date :"+date);
		
		Trigger cronTriggerBean = JobUtil.createCronTrigger(triggerKey, date, cronExpression, SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

		try {
			
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			Date dt = scheduler.scheduleJob(jobDetail, cronTriggerBean);
			
			System.out.println("Job with key jobKey :"+jobKey+ " and group :"+groupKey+ " scheduled successfully for date :"+dt);
			
			return true;
		} catch (SchedulerException e) {
			System.out.println("SchedulerException while scheduling job with key :"+jobKey + " message :"+e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Update one time scheduled job.
	 */
	@Override
	public boolean updateOneTimeJob(String jobName, Date date) {
		return false;
	}

	/**
	 * Update scheduled cron job.
	 */
	@Override
	public boolean updateCronJob(String jobName, Date date, String cronExpression) {
		return false;
	}
	
	/**
	 * Remove the indicated Trigger from the scheduler. 
	 * If the related job does not have any other triggers, and the job is not durable, then the job will also be deleted.
	 */
	@Override
	public boolean unScheduleJob(String jobName) {
		return false;
	}

	/**
	 * Delete the identified Job from the Scheduler - and any associated Triggers.
	 */
	@Override
	public boolean deleteJob(String jobName) {
		return false;
	}

	/**
	 * Pause a job
	 */
	@Override
	public boolean pauseJob(String jobName) {
		return false;
	}

	/**
	 * Resume paused job
	 */
	@Override
	public boolean resumeJob(String jobName) {
		return false;
	}

	/**
	 * Start a job now
	 */
	@Override
	public boolean startJobNow(String jobName) {
		return false;
	}
	

	/**
	 * Check if job is already running
	 */
	@Override
	public boolean isJobRunning(String jobName) {
		return false;
	}

	/**
	 * Get all jobs
	 */
	@Override
	public List<Map<String, Object>> getAllJobs() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					//get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					Date scheduleTime = triggers.get(0).getStartTime();
					Date nextFireTime = triggers.get(0).getNextFireTime();
					Date lastFiredTime = triggers.get(0).getPreviousFireTime();
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("jobName", jobName);
					map.put("groupName", jobGroup);
					map.put("scheduleTime", scheduleTime);
					map.put("lastFiredTime", lastFiredTime);
					map.put("nextFireTime", nextFireTime);
					
					
					if(isJobRunning(jobName)){
						map.put("jobStatus", "RUNNING");
					}else{
						String jobState = getJobState(jobName);
						map.put("jobStatus", jobState);
					}

					list.add(map);
					System.out.println("Job details:");
					System.out.println("Job Name:"+jobName + ", Group Name:"+ groupName + ", Schedule Time:"+scheduleTime);
				}

			}
		} catch (SchedulerException e) {
			System.out.println("SchedulerException while fetching all jobs. error message :"+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Check job exist with given name
	 */
	@Override
	public boolean isJobWithNamePresent(String jobName) {
		try {
			String groupKey = "YuktiGroup";
			JobKey jobKey = new JobKey(jobName, groupKey);
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			if (scheduler.checkExists(jobKey)){
				return true;
			}
		} catch (SchedulerException e) {
			System.out.println("SchedulerException while checking job with name and group exist:"+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Get the current state of job
	 */
	public String getJobState(String jobName) {
		return null;
	}

	/**
	 * Stop a job
	 */
	@Override
	public boolean stopJob(String jobName) {
		return false;
	}
}

