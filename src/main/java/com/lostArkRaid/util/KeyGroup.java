package com.lostArkRaid.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:com/key.properties")
public class KeyGroup {
	@Value("${discordkey}")
	private String discordkey;



	public String getDiscordkey() {
		return discordkey;
	}



	public void setDiscordkey(String discordkey) {
		this.discordkey = discordkey;
	}



	public void name() {
		System.out.println(discordkey);
	}
}
