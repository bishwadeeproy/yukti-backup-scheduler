package com.yukti.jobmanager.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yukti.jobmanager.data.repository.BackupJobCrudRepository;
import com.yukti.jobmanager.model.BackupJob;

@Service
public class BackupJobServiceImpl implements BackupJobService{
	
	@Autowired
	BackupJobCrudRepository repository;

	@Override
	public BackupJob save(BackupJob backupJob) {
		return repository.save(backupJob);
	}

}
