package com.employee.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	
	public static String empName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return (generatedString);
	}
	
	public static String empJob() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return (generatedString);
	}




}
