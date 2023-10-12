package com.lostArkRaid.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.lostArkRaid.controller.DisCordController;

import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.EnumSet;
import java.util.concurrent.Executor;


@EnableAsync
@EnableScheduling
@Configuration
@PropertySource("classpath:com/key.properties")
public class AppConfig extends ListenerAdapter{

  	  @Setter(onMethod_ = @Autowired)
	   private KeyGroup keys;
  	  @Value("${discordkey}")
  	   private String token;
  	  
  	 @Autowired
     private Environment env;
  	  
  	private static final String IVkey  = "IV"; // 초기화 벡터. 16byte. 128 bit. 임의값.
	private static final String encryptionKeykey = "encryptionKey"; // 비밀키. 32byte. 256 bit. 임의값.
  	
	//암호화 객체 생성
	@Bean
	public EndecryptService endecryptService() {
		System.out.println(env);
		EndecryptService endecryptService = new EndecryptImpl(env.getProperty(IVkey),env.getProperty(encryptionKeykey));
		return endecryptService;
	}
	
  	  //디스코드 봇 컨트롤러 객체 생성
    @Bean
    public JDA jda() throws Exception {
    
//      String token = keys.getDiscordkey();
      System.out.println(token);
        EnumSet<GatewayIntent> intents = EnumSet.of(
//            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.MESSAGE_CONTENT,
        	GatewayIntent.GUILD_MEMBERS, 
        	GatewayIntent.GUILD_MESSAGES, 
        	GatewayIntent.GUILD_MESSAGE_REACTIONS,
        	GatewayIntent.GUILD_PRESENCES
        	
        );
        
        EnumSet<CacheFlag> cacheFlags = EnumSet.of(
                CacheFlag.MEMBER_OVERRIDES,
                CacheFlag.VOICE_STATE
            );
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(intents);
        builder.enableCache(cacheFlags);
        //builder.addEventListeners(new DisCordController(taskExecutor()));
        JDA jda = builder.build();
        jda.addEventListener(new DisCordController(taskExecutor(),jda));
//        JDA jda = JDABuilder.createDefault(token, intents)
//            .build();
//        jda.getPresence().setStatus(OnlineStatus.ONLINE);
//        jda.addEventListener(new DisCordController());
        
        return jda;
    }

    
	/*
	 * @Bean public ListenerAdapter audioEchoExample() { return new
	 * DisCordController(taskExecutor()); }
	 */
    
    //멀티쓰레드 생성
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }
}
