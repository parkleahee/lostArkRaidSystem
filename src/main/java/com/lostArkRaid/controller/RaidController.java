package com.lostArkRaid.controller;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.lostArkRaid.service.CertificationService;
import com.lostArkRaid.vo.CharacterVo;
import com.lostArkRaid.vo.RaidSchedulerVO;

import lombok.Setter;
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/Raid/*")
public class RaidController {
	
	private static final Logger logger = LoggerFactory.getLogger(RaidController.class);
	
	   @Setter(onMethod_ = @Autowired)
	   private CertificationService service;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/makeRaid")
	public /*ResponseEntity<String>*/ void makeRaid(RaidSchedulerVO rv, Model model) {
		
	}

	

    
	
}
