package com.yukti.jobmanager.util.transformer;

import com.yukti.jobmanager.model.BackupJob;
import com.yukti.jobmanager.model.Job;

public class BackupJobToJobTransformer implements Transformer{

	@Override
	public Job transform(Object object) {
		BackupJob backupJob = (BackupJob) object;
		Job job = new Job();
		job.setJobName(backupJob.getBackupJobName());
		return job;
	}

}
