package com.yukti.pythoncontroller.controller.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yukti.pythoncontroller.controller.ApiError;
import com.yukti.pythoncontroller.controller.ServerResponse;
import com.yukti.pythoncontroller.controller.ServerResponseCode;
import com.yukti.pythoncontroller.controller.ServerResponseHandler;
import com.yukti.pythoncontroller.exception.handler.AlreadyExistsException;
import com.yukti.pythoncontroller.util.PythonJobManagerHelper;

/**
 * @author Deepak Chaurasia
 *
 */
@RestController
@RequestMapping("/pythonController/")
public class CreateBackupJobController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("createBackupJob")
	public ServerResponse create(@RequestParam("backupJobName") String backupJobName) {

		try {
			
			Map<String, String> jobName = new HashMap<String, String>();
			jobName.put("backupJobName", backupJobName);
			
			final String uri = PythonJobManagerHelper.buildURI(PythonJobManagerHelper.HTTP,
					PythonJobManagerHelper.JOB_MANAGER_SERVICE, PythonJobManagerHelper.JOB_MANAGER_API, jobName);

			ResponseEntity<ServerResponse> response = restTemplate.getForEntity(uri, ServerResponse.class);
			
			if(response.getBody().getStatusCode() == 409) {
				throw new AlreadyExistsException();
			}
			
			
			return ServerResponseHandler.getServerResponse(response.getBody().getStatusCode(),response.getBody().getData());
			

		} catch (AlreadyExistsException e) {

			ApiError apiError = new ApiError(HttpStatus.CONFLICT, e.getLocalizedMessage(),
					"BackupJob with name: " + backupJobName + " was already scheduled.");

			apiError.setStatusCode(HttpStatus.CONFLICT.value());

			return apiError;

		}
	}

}
