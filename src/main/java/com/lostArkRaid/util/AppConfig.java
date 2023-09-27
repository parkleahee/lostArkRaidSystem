package com.lostArkRaid.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.lostArkRaid.controller.DisCordController;

import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

@Configuration
@PropertySource("classpath:com/key.properties")
public class AppConfig extends ListenerAdapter{

  	  @Setter(onMethod_ = @Autowired)
	   private KeyGroup keys;
  	  @Value("${discordkey}")
  	   private String token;
    @Bean
    public JDA jda() throws Exception {
    
//      String token = keys.getDiscordkey();
      System.out.println(token);
//    	String token = "MTE1NTY3NDAyOTgyNzU1OTQ5Ng.Gkce7r.MfA0f4hMsrJ_xU60ypZY-zPhLWvlBtCKuUbV_s";
        EnumSet<GatewayIntent> intents = EnumSet.of(
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.MESSAGE_CONTENT
        );

        JDA jda = JDABuilder.createDefault(token, intents)
            .build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.addEventListener(new DisCordController());

        return jda;
    }

    
    @Bean
    public ListenerAdapter audioEchoExample() {
        return new DisCordController();
    }
    
}
