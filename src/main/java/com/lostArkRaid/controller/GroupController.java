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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lostArkRaid.service.CertificationService;
import com.lostArkRaid.service.GroupService;
import com.lostArkRaid.vo.CharacterVo;
import com.lostArkRaid.vo.UserVo;

import lombok.Setter;

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
		
		return "/manager/makeGroup";
	}
	@RequestMapping(value = "/checkGroupName")
	public ResponseEntity<String> checkGroupName(String groupName, Model model, HttpServletRequest req) {
		UserVo userVo= (UserVo) req.getAttribute("loginUser");
		return service.checkGroupName(groupName, userVo.getUserid()) ? new ResponseEntity<String>("O",HttpStatus.OK) : 
			new ResponseEntity<String>("X",HttpStatus.OK) ;
	}
	
}
