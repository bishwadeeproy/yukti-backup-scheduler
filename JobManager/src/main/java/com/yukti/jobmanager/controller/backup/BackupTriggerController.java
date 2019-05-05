package com.yukti.jobmanager.controller.backup;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yukti.jobmanager.controller.ServerResponse;
import com.yukti.jobmanager.controller.ServerResponseCode;
import com.yukti.jobmanager.model.Backup;
import com.yukti.jobmanager.model.BackupJob;
import com.yukti.jobmanager.service.backup.BackupService;
import com.yukti.jobmanager.service.job.BackupJobService;

@RestController
@RequestMapping("/backup/")
public class BackupTriggerController {

	@Autowired
	BackupService backupService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("trigger/{backupJobName}")
	public ServerResponse trigger(@PathVariable("backupJobName") String backupJobName) {
		Backup backup = new Backup();
		backup.setBackupJobName(backupJobName);
		backup.setStartTime(new Date());
		backup.setStatus("Running");
		//TODO: Call Python Controller to trigger legacy backup workflow
		return getServerResponse(ServerResponseCode.SUCCESS, backupService.save(backup));
	}

	public ServerResponse getServerResponse(int responseCode, Object data) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse;
	}

}


