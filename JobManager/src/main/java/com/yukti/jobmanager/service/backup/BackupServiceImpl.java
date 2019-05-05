package com.yukti.jobmanager.service.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yukti.jobmanager.data.repository.BackupCrudRepository;
import com.yukti.jobmanager.data.repository.BackupJobCrudRepository;
import com.yukti.jobmanager.model.Backup;
import com.yukti.jobmanager.model.BackupJob;

@Service
public class BackupServiceImpl implements BackupService{
	
	@Autowired
	BackupCrudRepository repository;

	@Override
	public Backup save(Backup backup) {
		return repository.save(backup);
	}

}
