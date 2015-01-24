package com.faubg.rea.security;

public abstract class tmpData {


	private static String originalPassword;

	public static String getOriginalPassword() {
		String returner = originalPassword;
		originalPassword = null;
		return returner;
	}

	public static void setOriginalPassword(String password) {
		originalPassword = password;
	}
}
