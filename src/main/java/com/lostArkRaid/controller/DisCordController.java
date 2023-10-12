package com.lostArkRaid.controller;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.emoji.EmojiAddedEvent;
import net.dv8tion.jda.api.events.emoji.EmojiRemovedEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateNameEvent;
import net.dv8tion.jda.api.events.emoji.update.EmojiUpdateRolesEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEmojiEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.thread.ThreadHiddenEvent;
import net.dv8tion.jda.api.events.thread.ThreadRevealedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.concurrent.Task;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.lostArkRaid.dao.DiscordDAO;
import com.lostArkRaid.dao.DiscordDAOImpl;
import com.lostArkRaid.service.CertificationService;
import com.lostArkRaid.service.CertificationServiceImpl;
import com.lostArkRaid.service.DMService;
import com.lostArkRaid.service.UserService;
import com.lostArkRaid.service.UserServiceImpl;
import com.lostArkRaid.util.AppConfig;
import com.lostArkRaid.util.ContextUtil;
import com.lostArkRaid.util.EndecryptService;
import com.lostArkRaid.util.KeyGroup;
import com.lostArkRaid.vo.CharacterVo;

import lombok.Setter;


public class DisCordController extends ListenerAdapter{
		private static List<Guild> guilds;
		private static HashMap<Guild, Task<List<Member>>> memberList= new HashMap<>();   
		private static HashMap<String, String> ownerApikey = new HashMap<String, String>();
		//ExecutorService executorService = Executors.newFixedThreadPool(5);

		private DiscordDAO ddao;
		private DMService dmService = new DMService();
	    private EndecryptService es;
	    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		private CertificationService cfService = new CertificationServiceImpl();
		private KeyGroup kg;
		private final Executor taskExecutor;
		private static JDA jda;
		public static JDA getJda() {
			return jda;
		}

		public static void setJda(JDA jda) {
			DisCordController.jda = jda;
		}

		@Autowired
		    public DisCordController(Executor taskExecutor, JDA jda) {
		        this.taskExecutor = taskExecutor;
		        this.jda = jda;
		    }
		
	    @Override
	    public void onMessageReceived(MessageReceivedEvent event)
	    {
	        Message message = event.getMessage();
	        User author = message.getAuthor();
	        String content = message.getContentRaw();
	        Guild guild = event.getGuild();
	        // Ignore message if bot
	        if (author.isBot()) {
	        	return;
	        }
	        //원하는 메시지 전송
	        System.out.println(author.getName());
	        System.out.println(author.getId());
	        System.out.println(author.getGlobalName());
	        System.out.println(author.getEffectiveName());
	        System.out.println(this.getClass());
	        // We only want to handle message in Guilds
	        if (!event.isFromGuild())
	        {
	            return;
	        }
	        if (content.startsWith("!"))
	        {
	        	if (content.equals("!맴버")) {
	        		System.out.println(es);
	        		taskExecutor.execute(() -> getMember(guild));
				}
	        	 if (content.startsWith("!검색")) {
	        		  String characterName = content.replace("!검색 ", "");
	        		 String apikey = ownerApikey.get(guild.getOwnerId());
	        		 //테스트용 api키
	        		 apikey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwODU5MjYifQ.Bo2I8xh9XWYTRArbvVTXixOlPwMPliHGUJ1l34eXiOoAu0uYVfNwlNCsLmdVo5lRn_J2YGHGBw6lqsSdTaS6iwrgJb-e24VP6n1mne4QiLLntqGa0gdjbSaAmCPK6hAA3i1wM-_VXQ5fVVGCGYGIca2GUQ8dsmZ7ENtOIdWCZhaMzPZlZIkOS_USrgeV3KxJKGYGAPIsSOy3LLh4tWkvlTNawQ26Ma0TqNV3IPusW5JItvwV7BWLM8pQDDj6p2QI6TVILZbBJfSR4wxALj36YVMK2R1iFgrkICgbvtidmERn7v0nOQLw9jzy8dQp0bIdZpkbAAyENG47NJl9O6VsLg";
	        		 CharacterVo cv =cfService.searchCharacter(apikey, characterName);
	        		// event.getChannel().sendMessage(cv.toString()).queue();
	        		 String imageUrl = cv.getCharacterImage();
	        		 event.getChannel().sendMessage(imageUrl).queue();
	        		 MessageCreateBuilder messageBuilder = new MessageCreateBuilder()
	        		     //.addContent(imageUrl + "\n")
	        		     .addContent("서버 : " + cv.getServerName() + "\n")
	        		     .addContent("캐릭터명 : " + cv.getCharacterName() + "\n")
	        		     .addContent("클래스 : " + cv.getCharacterClassName() + "\n")
	        		     .addContent("칭호 : " + cv.getTitle() + "\n")
	        		     .addContent("아이템레벨 : " + cv.getItemAvgLevel());

	        		 event.getChannel().sendMessage(messageBuilder.build()).queue();
	        	 }
	        	 if(content.equals("!DM")) {
	        		dmService.dm(author);
	        	 }
	        	 if(content.equals("!인증")) {
	        		 dmService.certification(author);
	        	 }

	        }//봇 명령어 ! 이프 끝
	        else if (content.equals("echo"))
	        {
	     //   	System.out.println("셧다운");
	    //    	event.getJDA().shutdownNow();
	            onEchoCommand(event);
	        }
	    }
	    public void onMessageReactionAdd(MessageReactionAddEvent event) {
	        // 이모지 반응을 처리하는 코드
	    	System.out.println("이모지 테스트");
	        User user = event.getUser();
	        MessageReaction reaction = event.getReaction();
	        String messageId = event.getMessageId();
	        String emoji = "";
	        if (user != null && !user.isBot()) {
	        	user.openPrivateChannel().queue(dms ->{
 					 dms.sendMessage("반응했어요").queue();
	        	});
			/*
			 * if (emoji.equals("👍")) { event.getChannel().sendMessage(user.getAsMention()
			 * + " 좋아요를 눌렀네요!").queue(); } else if (emoji.equals("👎")) {
			 * event.getChannel().sendMessage(user.getAsMention() + " 싫어요를 눌렀네요!").queue();
			 * }
			 */
	        	
	        }
	        
	        //메시지 주인에게 DM
	        if (user.isBot()) {
	        	return;
	        }
	        event.getChannel().retrieveMessageById(messageId).queue(message -> {
	            User messageAuthor = message.getAuthor();
	            User users = event.getUser();
	            // 메시지 작성자에게만 DM을 보내기 위해 작성자와 반응한 유저가 다른 경우에만 DM을 보냅니다.
	            if (messageAuthor != null && !messageAuthor.isBot() && !messageAuthor.getId().equals(users.getId())) {
	                messageAuthor.openPrivateChannel().queue(dms -> {
	                    dms.sendMessage("반응했어요").queue();
	                });
	            }
	        });
	    }
	    
	    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
	    	System.out.println("test6");
	    }
	    
	    @Override
	    public void onReady(ReadyEvent event) {
	    	if (this.es==null) {
	    		es = (EndecryptService) ContextUtil.getBean("endecryptService");
	    		System.out.println(es+"discord");
			}
	    	this.guilds = event.getJDA().getGuilds();
	    	if(ddao==null) {
	    		ddao = new DiscordDAOImpl((SqlSession)(ContextUtil.getBean("sqlSession")));
	    	}
	    	
	        for (Guild guild : guilds) {
	        	System.out.println(guild);
	        	System.out.println(es);
	        	String apikey = ddao.getApikey(guild.getOwner().getUser().getName());
	        	if (apikey != null) {
	        		ownerApikey.put(guild.getOwnerId(), (String)es.decryptAES(apikey));
				}
	        	
	        	System.out.println(ownerApikey.get(guild.getOwnerId()));
	            // 모든 멤버를 강제로 로드합니다. (오프라인 멤버 포함)
	        	this.memberList.put(guild, guild.loadMembers());
//	        	Task<List<Member>> a = guild.loadMembers();
//	            guild.loadMembers().onSuccess(members -> {
//	                System.out.println(guild.getName() + " 서버의 모든 멤버 목록:");
//	                
//	                for (Member member : members) {
//	                    System.out.println("사용자 이름: " + member.getUser().getName());
//	                    System.out.println("사용자 이름: " + member.getUser().getGlobalName());
//	                    System.out.println("사용자 ID: " + member.getUser().getId());
//	                }
//	            });
	        }
	    }
	    public void createDM(Guild guild) {
        	
      	  //for (Guild guild : guilds) {
      		 List<Member> members = memberList.get(guild).get();
      		 for(Member member : members) {
      			 if(!member.getUser().isBot()) {
      				 CacheRestAction<PrivateChannel> dm = member.getUser().openPrivateChannel();
      				 dm.onSuccess(dms ->{
      					 dms.sendMessage("test");
      				 });
      			 }
      		 }
      	  //}
      	
      }
      
      public void sendDM(User user) {
			
		}
	    public void getMember(Guild guild) {
	    	System.out.println(guild.getOwnerId());
	    	System.out.println(guild.getOwner().getUser().getName());
	    	List<Member> members = memberList.get(guild).get();
	    	for (Member member : members) {
	    		 User user = member.getUser();
	                System.out.println("사용자 이름: " + user.getName());
	                System.out.println("사용자 ID: " + user.getId());
	                System.out.println("사용자 닉네임: " + member.getEffectiveName());
	                System.out.println("사용자 역할: " + member.getRoles());
			}
	    }
	    
	    public void raidScheduler () {
	    	Calendar now = Calendar.getInstance();
	        int minutesUntilNextHour = 60 - now.get(Calendar.MINUTE);
	        int secondsUntilNextHour = 60 - now.get(Calendar.SECOND);
	        long initialDelay = TimeUnit.MINUTES.toSeconds(minutesUntilNextHour) +
	                            secondsUntilNextHour;

	        // 정각마다 실행될 메소드를 Runnable 객체로 정의
	        Runnable task = () -> {
	            // 여기에 정각에 실행될 코드를 작성
	        	Calendar currentTime = Calendar.getInstance();
	            int currentHourOfDay = currentTime.get(Calendar.HOUR_OF_DAY);
	        	ddao.getSchedule();
	        };

	        // 정각부터 주기적으로 실행
	        scheduler.scheduleAtFixedRate(task, initialDelay, 3600, TimeUnit.SECONDS);
		}
	    
	    //---------------------------------------------------------------------
	    private void onEchoCommand(MessageReceivedEvent event)
	    {
	        // Note: None of these can be null due to our configuration with the JDABuilder!
	        Member member = event.getMember();                              // Member is the context of the user for the specific guild, containing voice state and roles
	        GuildVoiceState voiceState = member.getVoiceState();            // Check the current voice state of the user
	        AudioChannel channel = voiceState.getChannel();                 // Use the channel the user is currently connected to
	        if (channel != null)
	        {
	            connectTo(channel);                                         // Join the channel of the user
	            onConnecting(channel, event.getChannel());                  // Tell the user about our success
	        }
	        else
	        {
	            onUnknownChannel(event.getChannel(), "your voice channel"); // Tell the user about our failure
	        }
	    }

	    private void onEchoCommand(MessageReceivedEvent event, Guild guild, String arg)
	    {
	        boolean isNumber = arg.matches("\\d+"); // This is a regular expression that ensures the input consists of digits
	        VoiceChannel channel = null;
	        if (isNumber)                           // The input is an id?
	        {
	            channel = guild.getVoiceChannelById(arg);
	        }
	        if (channel == null)                    // Then the input must be a name?
	        {
	            List<VoiceChannel> channels = guild.getVoiceChannelsByName(arg, true);
	            if (!channels.isEmpty())            // Make sure we found at least one exact match
	                channel = channels.get(0);      // We found a channel! This cannot be null.
	        }

	        MessageChannel messageChannel = event.getChannel();
	        if (channel == null)                    // I have no idea what you want mr user
	        {
	            onUnknownChannel(messageChannel, arg); // Let the user know about our failure
	            return;
	        }
	        connectTo(channel);                     // We found a channel to connect to!
	        onConnecting(channel, messageChannel);     // Let the user know, we were successful!
	    }

	    private void onConnecting(AudioChannel channel, MessageChannel messageChannel)
	    {
	        messageChannel.sendMessage("Connecting to " + channel.getName()).queue(); // never forget to queue()!
	    }

	    private void onUnknownChannel(MessageChannel channel, String comment)
	    {
	        channel.sendMessage("Unable to connect to ``" + comment + "``, no such channel!").queue(); // never forget to queue()!
	    }

	    private void connectTo(AudioChannel channel)
	    {
	        Guild guild = channel.getGuild();
	        // Get an audio manager for this guild, this will be created upon first use for each guild
	        AudioManager audioManager = guild.getAudioManager();
	        // Create our Send/Receive handler for the audio connection
	        EchoHandler handler = new EchoHandler();

	        // The order of the following instructions does not matter!

	        // Set the sending handler to our echo system
	        audioManager.setSendingHandler(handler);
	        // Set the receiving handler to the same echo system, otherwise we can't echo anything
	        audioManager.setReceivingHandler(handler);
	        // Connect to the voice channel
	        audioManager.openAudioConnection(channel);
	    }

	    public static class EchoHandler implements AudioSendHandler, AudioReceiveHandler
	    {
	        /*
	            All methods in this class are called by JDA threads when resources are available/ready for processing.

	            The receiver will be provided with the latest 20ms of PCM stereo audio
	            Note you can receive even while setting yourself to deafened!

	            The sender will provide 20ms of PCM stereo audio (pass-through) once requested by JDA
	            When audio is provided JDA will automatically set the bot to speaking!
	         */
	        private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();

	        /* Receive Handling */

	        @Override // combine multiple user audio-streams into a single one
	        public boolean canReceiveCombined()
	        {
	            // limit queue to 10 entries, if that is exceeded we can not receive more until the send system catches up
	            return queue.size() < 10;
	        }

	        @Override
	        public void handleCombinedAudio(CombinedAudio combinedAudio)
	        {
	            // we only want to send data when a user actually sent something, otherwise we would just send silence
	            if (combinedAudio.getUsers().isEmpty())
	                return;

	            byte[] data = combinedAudio.getAudioData(1.0f); // volume at 100% = 1.0 (50% = 0.5 / 55% = 0.55)
	            queue.add(data);
	        }
	/*
	        Disable per-user audio since we want to echo the entire channel and not specific users.

	        @Override // give audio separately for each user that is speaking
	        public boolean canReceiveUser()
	        {
	            // this is not useful if we want to echo the audio of the voice channel, thus disabled for this purpose
	            return false;
	        }

	        @Override
	        public void handleUserAudio(UserAudio userAudio) {} // per-user is not helpful in an echo system
	*/

	        /* Send Handling */

	        @Override
	        public boolean canProvide()
	        {
	            // If we have something in our buffer we can provide it to the send system
	            return !queue.isEmpty();
	        }

	        @Override
	        public ByteBuffer provide20MsAudio()
	        {
	            // use what we have in our buffer to send audio as PCM
	            byte[] data = queue.poll();
	            return data == null ? null : ByteBuffer.wrap(data); // Wrap this in a java.nio.ByteBuffer
	        }

	        @Override
	        public boolean isOpus()
	        {
	            // since we send audio that is received from discord we don't have opus but PCM
	            return false;
	        }
	        
	        
	    }
}
