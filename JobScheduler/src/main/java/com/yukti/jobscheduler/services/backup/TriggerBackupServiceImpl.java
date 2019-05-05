package com.yukti.jobscheduler.services.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yukti.jobscheduler.util.JobSchedulerHelper;


@Service
public class TriggerBackupServiceImpl implements TriggerBackupService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public boolean triggerBackup(String backupJobName) {
		final String uri = JobSchedulerHelper.buildURI(JobSchedulerHelper.HTTP, JobSchedulerHelper.JOB_MANAGER_SERVICE, (JobSchedulerHelper.JOB_MANAGER_TRIGGER_BACKUP_API+JobSchedulerHelper.URL_SLASH_DELIMETR+backupJobName));
		 
	    String result = restTemplate.getForObject( uri, String.class,String.class);
	 
	    System.out.println(result);
	    
		return true;
		
	}

}
