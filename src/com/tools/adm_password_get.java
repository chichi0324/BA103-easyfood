package com.tools;

public class adm_password_get {
	
	public static String producePas(){
		int pas1 = (int)(Math.random()*10);
		int pas2 = (int)(Math.random()*10);
		int pas3 = (int)(Math.random()*10);
		String password="eat"+pas1+pas2+pas3;
		
		return password;
	}
	

}
