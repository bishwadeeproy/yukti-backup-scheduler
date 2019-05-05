package com.yukti.jobscheduler.util;

public class JobSchedulerHelper {
	
	public final static String HTTP = "http";
	public final static String URL_PROTOCOL_DELIMETR = ":";
	public final static String URL_SLASH_DELIMETR = "/";
	public final static String JOB_MANAGER_SERVICE = "JOB-MANAGER-SERVICE";
	public final static String JOB_MANAGER_TRIGGER_BACKUP_API = "backup/trigger";
	
	public static String buildURI(String protocol,String serviceName, String apiEndPoint) {
		
		StringBuilder serviceUrl = new StringBuilder();
		serviceUrl.append(protocol);
		serviceUrl.append(URL_PROTOCOL_DELIMETR);
		serviceUrl.append(URL_SLASH_DELIMETR);
		serviceUrl.append(URL_SLASH_DELIMETR);
		serviceUrl.append(serviceName);
		serviceUrl.append(URL_SLASH_DELIMETR);
		serviceUrl.append(apiEndPoint);
	
		System.out.println("API====>" + serviceUrl.toString());
		
		return serviceUrl.toString();
		
	}
	
	

}
