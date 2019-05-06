package com.yukti.jobmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Bishwadeep Roy
 *
 */
@Entity
@Table(name = "backupjobs")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@backupJobId", scope = BackupJob.class)
public class BackupJob implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5591105786493707669L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int backupJobId;
	
	@Column(name = "backupjobname", unique=true)
	private String backupJobName;
	
	

	public BackupJob() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BackupJob(String backupJobName) {
		super();
		this.backupJobName = backupJobName;
	}

	public int getBackupJobId() {
		return backupJobId;
	}

	public void setBackupJobId(int backupJobId) {
		this.backupJobId = backupJobId;
	}

	public String getBackupJobName() {
		return backupJobName;
	}

	public void setBackupJobName(String backupJobName) {
		this.backupJobName = backupJobName;
	}

}
