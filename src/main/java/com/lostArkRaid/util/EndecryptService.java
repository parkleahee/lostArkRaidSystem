package com.lostArkRaid.util;


public interface EndecryptService {
	
	public Object encryptAES(String originData);
	
	public Object decryptAES(String encryptedData);
	
	public String encryptSHA(String originData);

}
