package com.yukti.jobmanager.controller.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.yukti.jobmanager.controller.ApiError;
import com.yukti.jobmanager.controller.ServerResponse;
import com.yukti.jobmanager.controller.ServerResponseCode;
import com.yukti.jobmanager.controller.ServerResponseHandler;
import com.yukti.jobmanager.exception.handler.AlreadyExistsException;
import com.yukti.jobmanager.model.BackupJob;
import com.yukti.jobmanager.model.Job;
import com.yukti.jobmanager.service.job.BackupJobService;
import com.yukti.jobmanager.util.JobManagerHelper;
import com.yukti.jobmanager.util.transformer.BackupJobToJobTransformer;

/**
 * @author Bishwadeep Roy
 *
 */
@RestController
@RequestMapping("/backupJob/")
public class BackupJobController {

	@Autowired
	BackupJobService backupJobService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("create")
	public ServerResponse save(@RequestParam("backupJobName") String backupJobName) {

		try {
			BackupJob backupJob = new BackupJob(backupJobName);

			Job job = new BackupJobToJobTransformer().transform(backupJobService.save(backupJob));

			final String uri = JobManagerHelper.buildURI(JobManagerHelper.HTTP, JobManagerHelper.JOB_SCHEDULER_SERVICE,
					JobManagerHelper.JOB_SCHEDULE_API);

			return ServerResponseHandler.getServerResponse(ServerResponseCode.SUCCESS, restTemplate.postForObject(uri, job, Job.class));

		} catch (AlreadyExistsException e) {

			ApiError apiError = new ApiError(HttpStatus.CONFLICT, e.getLocalizedMessage(),
					"BackupJob with name: " + backupJobName + " was already scheduled.");
			
			apiError.setStatusCode(HttpStatus.CONFLICT.value());
			
			return apiError;

			
		}
	}

	

}
