package com.lostArkRaid.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lostArkRaid.service.CertificationService;
import com.lostArkRaid.service.UserService;
import com.lostArkRaid.vo.CharacterVo;
import com.lostArkRaid.vo.UserVo;


import lombok.Setter;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user/*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Setter(onMethod_ = @Autowired)
	   private CertificationService cfservice;
	
	@Setter(onMethod_ = @Autowired)
	   private UserService service;
	
	@RequestMapping(value = "/login")
	public String login(String userid, String userpw, Model model, HttpServletRequest req) throws Exception {
		//return "/main/main";
		System.out.println(userid);
		System.out.println(userpw);
		 if(service.userLogin(userid, userpw, req)!=null){ return "/main/main"; }else
		 { return "home"; }
		 
	}
	
	@RequestMapping(value = "/join")
	public String join(Model model) {
		
		return "/user/joinview";
	}
	
	@RequestMapping(value = "/searchCharcater")
	public ResponseEntity<ArrayList<CharacterVo>> searchCharcater(String characterName,String userApiKey,Model model) {
		ArrayList<CharacterVo> cvar = cfservice.searchAllCharacter(userApiKey, characterName);
		return new ResponseEntity<ArrayList<CharacterVo>>(cvar,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/choiceCharcater")
	public ResponseEntity<CharacterVo> choiceCharcater(String characterName,String userApiKey,Model model) {
		CharacterVo cvar = cfservice.searchCharacter(userApiKey, characterName);
		return new ResponseEntity<CharacterVo>(cvar,HttpStatus.OK);
	}
	
	
	@PostMapping("joinok")
	public String joinok(UserVo uservo, String search_characterName, RedirectAttributes ra) {
		System.out.println(uservo.getUserApiKey());
		CharacterVo cvar = cfservice.searchCharacter(uservo.getUserApiKey(), search_characterName);
		if (cvar.getCharacterName()==null||cvar.getCharacterName().equals("")) {
			return "/user/joinview";
		}
		uservo.setUserCharacterVo(cvar);
		 if(service.joinok(uservo)) {
			 ra.addFlashAttribute("joinok","t");
			 return "redirect:home";
		 }else {
			 return"/user/joinview";
		 }
	}
}
