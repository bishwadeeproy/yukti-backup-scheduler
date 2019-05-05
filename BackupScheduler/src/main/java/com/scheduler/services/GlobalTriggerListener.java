package com.scheduler.services;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.springframework.stereotype.Component;
import org.quartz.TriggerListener;

@Component
public class GlobalTriggerListener implements TriggerListener {
	
	private static final String TRIGGER_LISTENER_NAME = "BackupSchedulerGlobalTriggerListener";
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return TRIGGER_LISTENER_NAME;
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		String triggerName = context.getJobDetail().getKey().toString();
        System.out.println("triggerFired : " + triggerName + " is fired");
		
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO Auto-generated method stub
		
	}

}
