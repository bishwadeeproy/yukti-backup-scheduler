package com.yukti.jobmanager.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yukti.jobmanager.model.Backup;

/**
 * @author Bishwadeep Roy
 *
 */
@Repository
public interface BackupCrudRepository  extends CrudRepository<Backup, Long>{

}
