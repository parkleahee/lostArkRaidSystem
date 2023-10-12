package com.lostArkRaid.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lostArkRaid.service.CertificationService;
import com.lostArkRaid.util.ContextUtil;
import com.lostArkRaid.util.EndecryptImpl;
import com.lostArkRaid.util.EndecryptService;
import com.lostArkRaid.util.KeyGroup;

import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Setter(onMethod_ = @Autowired)
    private EndecryptService es;
	  
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
//		guild.loadMembers()
//		for (Guild guild : guilds) {
//     		 List<Member> members = .get();
//     		 for(Member member : members) {
//     			 if(!member.getUser().isBot()) {
//     			 }
//     		 }
		
		return "home";
	}
	
}
