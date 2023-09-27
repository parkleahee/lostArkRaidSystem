package com.lostArkRaid.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lostArkRaid.dao.UserDAO;
import com.lostArkRaid.dao.UserDAOImpl;
import com.lostArkRaid.vo.UserVo;

import lombok.Setter;

@Service
public class UserServiceImpl implements UserService {

	@Setter(onMethod_ = @Autowired)
	UserDAO udao = new UserDAOImpl();


	@Override
	public UserVo userLogin(String userid, String userpw, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
//		userid = req.getParameter("userid");
//		userpw = req.getParameter("userpw");
		UserVo loginUser = udao.userLogin(userid, userpw);
		
		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
//         ChatServer.getNowloginUser().add(loginUser.getUserid());
			return loginUser;
		} else {
			return null;
		}
	}



	   @Override
		public boolean joinok(UserVo udto) {
			return udao.joinok(udto);
		}
	   
	   
}