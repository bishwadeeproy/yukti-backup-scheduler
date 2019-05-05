package com.yukti.jobmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Bishwadeep Roy
 *
 */

@Entity
@Table(name = "backups")
public class Backup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2195808211808103962L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "backupJobName")
	private String backupJobName;
	
	@Column(name = "startTime")
	private Date startTime;
	
	@Column(name = "status")
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBackupJobName() {
		return backupJobName;
	}

	public void setBackupJobName(String backupJobName) {
		this.backupJobName = backupJobName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
