package com.yukti.jobmanager.util.transformer;

import com.yukti.jobmanager.model.BackupJob;
import com.yukti.jobmanager.model.Job;

public class JobToBackupJobTransformer implements Transformer{

	@Override
	public BackupJob transform(Object object) {
		Job job = (Job) object;
		BackupJob backupJob = new BackupJob();
		backupJob.setBackupJobName(job.getJobName());
		return backupJob;
	}
}
