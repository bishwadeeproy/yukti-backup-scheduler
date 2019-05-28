package com.yukti.pythoncontroller.controller.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.yukti.pythoncontroller.controller.ApiError;
import com.yukti.pythoncontroller.controller.ServerResponse;
import com.yukti.pythoncontroller.controller.ServerResponseHandler;
import com.yukti.pythoncontroller.exception.handler.AlreadyExistsException;
import com.yukti.pythoncontroller.util.PythonJobManagerHelper;

/**
 * @author Shivani Kate
 *
 */
@RestController
@RequestMapping("/pythonController/")
public class TriggerBackupJobController {


	@RequestMapping("triggerBackupJob")
	public ServerResponse create(@RequestParam("backupJobName") String backupJobName) {

		try {
			
			Map<String, String> jobName = new HashMap<String, String>();
			jobName.put("backupJobName", backupJobName);
			
			final String uri = PythonJobManagerHelper.buildURI(PythonJobManagerHelper.HTTP,
					PythonJobManagerHelper.PYTHON_API, PythonJobManagerHelper.TRIGGER_JOB_API, jobName);

			ResponseEntity<String> response = new RestTemplate().getForEntity(uri, String.class);
			
			return ServerResponseHandler.getServerResponse(response.getStatusCodeValue(),response.getBody());
			

		}
		catch (HttpClientErrorException httpEx) {
			ApiError apiError = new ApiError(httpEx.getStatusCode(), httpEx.getLocalizedMessage(), "Unable to trigger Job "+backupJobName);
			apiError.setStatusCode(httpEx.getStatusCode().value());
			return apiError;
		}
		
		catch (ResourceAccessException RAEx) {
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to contact backend server. Make sure the API server is running.", "Unable to trigger Job "+backupJobName);
			apiError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

			return apiError;
		}
		
		catch (Exception e) {
			
			System.out.println(e);

			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(),
					"Something Went wrong");

			apiError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

			return apiError;

		}
	}

}
