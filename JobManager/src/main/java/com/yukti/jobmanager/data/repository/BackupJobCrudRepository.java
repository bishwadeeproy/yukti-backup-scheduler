package com.yukti.jobmanager.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yukti.jobmanager.model.BackupJob;

/**
 * @author Bishwadeep Roy
 *
 */
@Repository
public interface BackupJobCrudRepository extends CrudRepository<BackupJob, Long>{

}
