package com.lostArkRaid.dao;

import java.util.List;

import com.lostArkRaid.vo.UserVo;

//데이터 확인
public interface UserDAO {
	
	public UserVo userLogin(String userid, String userpw);

	public boolean checkid(String userid);

	public boolean joinok(UserVo udto);

	public String findid(String phone);

	public UserVo findpw(String userid);

	public void centificationCode(String discordName, String randomHexString);

	
		
}
