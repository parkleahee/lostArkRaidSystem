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

import lombok.Setter;
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/lostArk/*")
public class LostArkController {
	
	private static final Logger logger = LoggerFactory.getLogger(LostArkController.class);
	
	   @Setter(onMethod_ = @Autowired)
	   private CertificationService service;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/getInfo")
	public String getInfo(String characterName, Model model) {
		String apikey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwODU5MjYifQ.Bo2I8xh9XWYTRArbvVTXixOlPwMPliHGUJ1l34eXiOoAu0uYVfNwlNCsLmdVo5lRn_J2YGHGBw6lqsSdTaS6iwrgJb-e24VP6n1mne4QiLLntqGa0gdjbSaAmCPK6hAA3i1wM-_VXQ5fVVGCGYGIca2GUQ8dsmZ7ENtOIdWCZhaMzPZlZIkOS_USrgeV3KxJKGYGAPIsSOy3LLh4tWkvlTNawQ26Ma0TqNV3IPusW5JItvwV7BWLM8pQDDj6p2QI6TVILZbBJfSR4wxALj36YVMK2R1iFgrkICgbvtidmERn7v0nOQLw9jzy8dQp0bIdZpkbAAyENG47NJl9O6VsLg";
		CharacterVo cv = service.searchCharacter(apikey, characterName);
		model.addAttribute("character",cv);
		return "/main/test";
	}
	@RequestMapping(value = "/getAllInfo")
	public String getAllInfo(String characterName, Model model) {
		String apikey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwODU5MjYifQ.Bo2I8xh9XWYTRArbvVTXixOlPwMPliHGUJ1l34eXiOoAu0uYVfNwlNCsLmdVo5lRn_J2YGHGBw6lqsSdTaS6iwrgJb-e24VP6n1mne4QiLLntqGa0gdjbSaAmCPK6hAA3i1wM-_VXQ5fVVGCGYGIca2GUQ8dsmZ7ENtOIdWCZhaMzPZlZIkOS_USrgeV3KxJKGYGAPIsSOy3LLh4tWkvlTNawQ26Ma0TqNV3IPusW5JItvwV7BWLM8pQDDj6p2QI6TVILZbBJfSR4wxALj36YVMK2R1iFgrkICgbvtidmERn7v0nOQLw9jzy8dQp0bIdZpkbAAyENG47NJl9O6VsLg";
		ArrayList<CharacterVo> cv = service.searchAllCharacter(apikey, characterName);
		model.addAttribute("character",cv);
		return "/main/test2";
	}
	

    
	
}
