package com.lostArkRaid.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lostArkRaid.vo.UserVo;



public interface UserService {
	
	public UserVo userLogin(String userid, String userpw, HttpServletRequest req) throws Exception;
	public boolean joinok(UserVo udto);

}
