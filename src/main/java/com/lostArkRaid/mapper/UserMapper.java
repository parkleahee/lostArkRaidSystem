package com.lostArkRaid.mapper;


import com.lostArkRaid.vo.UserVo;


public interface UserMapper {
	public UserVo userLogin();
	public int checkId();
	public int userJoin();
	public String findid();
	public String findpw();
	public String getApikey();
	
	}
