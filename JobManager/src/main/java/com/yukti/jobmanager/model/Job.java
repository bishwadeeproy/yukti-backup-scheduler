package com.yukti.jobmanager.model;

import java.io.Serializable;

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
@Table(name = "jobs")
public class Job implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5783193459086138710L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int jobId;
	
	@Column(name = "jobname")
	private String jobName;

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
