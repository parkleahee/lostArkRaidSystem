package com.lostArkRaid.util;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


//@Service
public class EndecryptImpl implements EndecryptService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndecryptImpl.class);
	
	
	/**
	 * 비밀키 encryptionKey, 초기화벡터 IV
	 * 값 변경하게 되면 이 키를 사용해 암호화했던 
	 * 기존 데이터들의 복호화가 불가능해지므로 변경하지 말 것을 권장함.
	 * */
//	private static final String IVkey  = "IV"; // 초기화 벡터. 16byte. 128 bit. 임의값.
//	private static final String encryptionKeykey = "encryptionKey"; // 비밀키. 32byte. 256 bit. 임의값.
	
	private final String IV;
	private final String encryptionKey;
	
	public EndecryptImpl(String iv, String encryptionKey) {
		this.IV =  iv;
		this.encryptionKey =  encryptionKey;
		System.out.println(IV);
		System.out.println(this.encryptionKey);
	}
	
	/**
	 * AES 암호화 메서드. 
	 * 알고리즘 : AES-256
	 * Encoding : UTF-8
	 * @param String originData 암호화할 평문
	 * @return java.lang.Object 암호화된 평문
	 * @exception NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	 *  		  InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	 * */
	public Object encryptAES(String originData) {

		// param 으로 받은 key/iv 가공.
		byte[] keyData = encryptionKey.getBytes(Charset.forName("UTF-8")); // 비밀키 byte[] 로.
		byte[] ivData = IV.getBytes(Charset.forName("UTF-8"));
		SecretKey secretKey = new SecretKeySpec(keyData, "AES"); // AES-256
		IvParameterSpec IV = new IvParameterSpec(ivData);

		// 암호화 담당 부분
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 암호화모드
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV); // 암호화 시작.
			byte[] encrpyedMessageBytes = cipher.doFinal(originData.getBytes(Charset.forName("UTF-8"))); // AES-256
																											
			return new String(Base64.encodeBase64(encrpyedMessageBytes)); // Base64 로 인코딩.

		} catch (NoSuchPaddingException | NoSuchAlgorithmException
				| InvalidAlgorithmParameterException |  InvalidKeyException
				|  IllegalBlockSizeException |  BadPaddingException e) {
				e.printStackTrace();
				System.out.println("encrypt error : " + e);
			}
			return null;
		}

	

	/**
	 * AES 복호화 메서드. 
	 * 알고리즘 : AES-256
	 * Encoding : UTF-8
	 * @param String encryptedData 암호화된 평문
	 * @return java.lang.Object 복호화된 평문
	 * @exception NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
	 *  		  InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	 * */
	public Object decryptAES(String encryptedData) {

		// param 으로 받은 값들 가공.
		byte[] keyData = encryptionKey.getBytes(Charset.forName("UTF-8")); // 비밀키 byte[] 로.
		byte[] ivData = IV.getBytes(Charset.forName("UTF-8"));
		byte[] decrThis = encryptedData.getBytes(Charset.forName("UTF-8"));

		SecretKey secretKey = new SecretKeySpec(keyData, "AES"); // AES-256
		IvParameterSpec IV = new IvParameterSpec(ivData);

		// 복호화 담당 부분
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 암호화모드
			cipher.init(Cipher.DECRYPT_MODE, secretKey, IV);
			byte[] decryptedMessageBytes = Base64.decodeBase64(decrThis); // Base64 로 디코딩.

			return new String(cipher.doFinal(decryptedMessageBytes), Charset.forName("UTF-8")); // Base64 디코딩된 값을
																								// AES-256으로 디코딩.

		} catch (Exception e) {
				e.printStackTrace();
				System.out.println("decrypt error : " + e);
			}
			return null;
		}

	

	/**
	 * SHA-256 단방향 암호화 (비밀번호 전용) 메서드. 
	 * 알고리즘 : SHA-256
	 * Encoding : UTF-8
	 * @param String originData 평문 password
	 * @return String hexStr.append(hex) 암호화된 password
	 * @exception Exception
	 * */
	public String encryptSHA(String originData) {

	try {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(originData.getBytes(Charset.forName("UTF-8")));
		StringBuffer hexStr = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]); // 16진수
			if (hex.length() == 1)
				hexStr.append('0');
			hexStr.append(hex);
		}

		return hexStr.toString();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("encryptSHA error : " + e);
		
		}
		return null;
	}

	
	
}
	


