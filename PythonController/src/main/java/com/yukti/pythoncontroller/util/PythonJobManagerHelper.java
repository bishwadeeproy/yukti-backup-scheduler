package com.yukti.pythoncontroller.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class PythonJobManagerHelper {

	public final static String HTTP = "http";
	public final static String URL_PROTOCOL_DELIMETR = ":";
	public final static String URL_SLASH_DELIMETR = "/";
	public final static String JOB_MANAGER_SERVICE = "JOB-MANAGER-SERVICE";
	public final static String JOB_MANAGER_API = "backupJob/create";
	public static final String TRIGGER_JOB_API = "backupJob/addjob";
	public static final String PYTHON_API = "localhost:9090";

	public static String buildURI(String protocol, String serviceName, String apiEndPoint,
			Map<String, String> jobName) {

		StringBuilder serviceUrl = new StringBuilder();
		serviceUrl.append(protocol);
		serviceUrl.append(URL_PROTOCOL_DELIMETR);
		serviceUrl.append(URL_SLASH_DELIMETR);
		serviceUrl.append(URL_SLASH_DELIMETR);
		serviceUrl.append(serviceName);
		serviceUrl.append(URL_SLASH_DELIMETR);
		serviceUrl.append(apiEndPoint);
		serviceUrl.append(addQueryParams(jobName));

		System.out.println("API====>" + serviceUrl.toString());

		return serviceUrl.toString();

	}

	private static String addQueryParams(Map<String, String> jobName) {

		if (jobName.keySet().size() == 0) {
			return "";
		}

		Iterator<Map.Entry<String, String>> itr = jobName.entrySet().iterator();

		String params = "";

		if (itr.hasNext()) {

			Entry<String, String> param = itr.next();
			params = "?" + param.getKey() + "=" + param.getValue();
		}
		while (itr.hasNext()) {
			Entry<String, String> param = itr.next();
			params += "&" + param.getKey() + "=" + param.getValue();
		}

		return params;
	}

}
