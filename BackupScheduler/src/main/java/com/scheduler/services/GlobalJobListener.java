package com.scheduler.services;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.stereotype.Component;

@Component
public class GlobalJobListener implements JobListener{
	
	private static final String JOB_LISTENER_NAME = "BackupSchedulerGlobalJobListener";
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return JOB_LISTENER_NAME;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		// TODO Auto-generated method stub
		
	}

}
