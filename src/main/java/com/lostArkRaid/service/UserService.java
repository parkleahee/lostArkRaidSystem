package com.lostArkRaid.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lostArkRaid.vo.UserVo;



public interface UserService {
	
	public UserVo userLogin(String userid, String userpw, HttpServletRequest req) throws Exception;
	public boolean joinok(UserVo udto);
	public boolean discordCentification(String discordName, String centificationCode);
	public void centificationCode(String discordName, String randomHexString);
	public boolean centificationSend(String discordName);

}
