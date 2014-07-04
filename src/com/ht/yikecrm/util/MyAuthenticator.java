package com.ht.yikecrm.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 验证密码
 * @author HongToo
 *
 */
public class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 */
	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

}
