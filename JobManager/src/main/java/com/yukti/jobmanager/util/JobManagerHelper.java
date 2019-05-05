package com.yukti.jobmanager.util;

public class JobManagerHelper {
	
	public final static String HTTP = "http";
	public final static String URL_PROTOCOL_DELIMETR = ":";
	public final static String URL_SLASH_DELIMETR = "/";
	public final static String JOB_SCHEDULER_SERVICE = "JOB-SCHEDULER-SERVICE";
	public final static String JOB_SCHEDULE_API = "scheduler/schedule";
	
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
