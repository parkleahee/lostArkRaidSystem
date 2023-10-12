package com.lostArkRaid.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lostArkRaid.service.CertificationService;
import com.lostArkRaid.service.GroupService;
import com.lostArkRaid.util.ContextUtil;
import com.lostArkRaid.vo.CharacterVo;
import com.lostArkRaid.vo.UserVo;

import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/group/*")
public class GroupController {
	
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	   @Setter(onMethod_ = @Autowired)
	   private GroupService service;
	
	@RequestMapping(value = "/makeGroup")
	public String makeGroup(Locale locale, Model model) {
		JDA jda = (JDA)ContextUtil.getBean("jda");
		List<Guild> guilds = jda.getGuilds();
		for (Guild guild : guilds) {
			 List<Member> members = guild.loadMembers().get();
			 for(Member member : members) {
     			 if(!member.getUser().isBot()) {
     				 member.getUser().openPrivateChannel().queue(dms ->{
      					 dms.sendMessage("home에 입장하셨습니다").queue();
      				 });
     			 }
			 }
		}
		return "/manager/makeGroup";
	}
	@RequestMapping(value = "/checkGroupName")
	public ResponseEntity<String> checkGroupName(String groupName, Model model, HttpServletRequest req) {
		UserVo userVo= (UserVo) req.getSession().getAttribute("loginUser");
		System.out.println(userVo);
		return service.checkGroupName(groupName) ? new ResponseEntity<String>("O",HttpStatus.OK) : 
			new ResponseEntity<String>("X",HttpStatus.OK) ;
	}
	
}
