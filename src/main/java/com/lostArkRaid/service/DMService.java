package com.lostArkRaid.service;

import java.security.SecureRandom;

import net.dv8tion.jda.api.entities.User;

public class DMService {
	public void dm(User author) {
    		 //createDM(guild);
    		 author.openPrivateChannel().queue(dms ->{
					 dms.sendMessage("test").queue();
				 });

	}
	
	public void certification(User author) {

        // 랜덤 시드 생성
        SecureRandom random = new SecureRandom();
        // 랜덤 16진수 문자열 생성
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomInt = random.nextInt(16); // 0부터 15까지의 랜덤 정수
            hexString.append(Integer.toHexString(randomInt));
        }

        String randomHexString = hexString.toString();
        System.out.println("랜덤 16진수 문자열 (8자리): " + randomHexString);
		 author.openPrivateChannel().queue(dms ->{
				 dms.sendMessage(randomHexString).queue();
			 });
	}
}
