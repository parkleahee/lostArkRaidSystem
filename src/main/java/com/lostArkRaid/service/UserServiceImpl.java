package com.lostArkRaid.service;

import java.security.SecureRandom;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lostArkRaid.dao.UserDAO;
import com.lostArkRaid.dao.UserDAOImpl;
import com.lostArkRaid.util.ContextUtil;
import com.lostArkRaid.vo.UserVo;

import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

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
	   
	   @Override
	public boolean discordCentification(String discordName, String centificationCode) {
		// TODO Auto-generated method stub
		return true;
	}
	
	   @Override
	public void centificationCode(String discordName, String randomHexString) {
		// TODO Auto-generated method stub
		
	}
	   
	   @Override
	public boolean centificationSend(String discordName) {
		   // 랜덤 시드 생성
	        SecureRandom random = new SecureRandom();

	        // 랜덤 16진수 문자열 생성
	        StringBuilder hexString = new StringBuilder();
	        for (int i = 0; i < 8; i++) {
	            int randomInt = random.nextInt(16); // 0부터 15까지의 랜덤 정수
	            hexString.append(Integer.toHexString(randomInt));
	        }

	        String randomHexString = hexString.toString();
			JDA jda = (JDA)ContextUtil.getBean("jda");
			List<Guild> guilds = jda.getGuilds();
			for (Guild guild : guilds) {
								 List<Member> members = guild.loadMembers().get();
								 for(Member member : members) {
									 User user = member.getUser();
					     			 if(!user.isBot()) {
					     				 if (user.getName().equals(discordName)) {
					     					user.openPrivateChannel().queue(dms ->{
					     						 dms.sendMessage("인증번호 : "+randomHexString).queue();
					     					 });
					     					udao.centificationCode(discordName,randomHexString);
					     					return true;
										}
					     			 }
								 }
							}
		return false;
	}
}