package com.lostArkRaid.controller;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.*;
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
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.concurrent.Task;

import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import com.lostArkRaid.service.CertificationService;

import lombok.Setter;


public class DisCordController extends ListenerAdapter{
		List<Guild> guilds;
		HashMap<Guild, Task<List<Member>>> memberList= new HashMap<>();   
		//ExecutorService executorService = Executors.newFixedThreadPool(5);
		@Setter(onMethod_ = @Autowired)
		  private CertificationService cfservice;
		
		  private final Executor taskExecutor;
		    @Autowired

		    public DisCordController(Executor taskExecutor) {
		        this.taskExecutor = taskExecutor;
		    }
		
	    @Override
	    public void onMessageReceived(MessageReceivedEvent event)
	    {
	    	System.out.println("test0");
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
	        System.out.println(author.getAvatarId());
	        
	        event.getChannel().sendMessage("test").queue();
	        // We only want to handle message in Guilds
	        if (!event.isFromGuild())
	        {
	            return;
	        }

	        if (content.startsWith("!"))
	        {
	        	if (content.equals("!맴버")) {
	        		taskExecutor.execute(() -> getMember(guild));
	        		 
				}
	        }
	        else if (content.equals("!echo"))
	        {
	            onEchoCommand(event);
	        }
	    }
	    public void onMessageReactionAdd(MessageReactionAddEvent event) {
	        // 이모지 반응을 처리하는 코드
	    	System.out.println("이모지 테스트");
	        User user = event.getUser();
	        MessageReaction reaction = event.getReaction();
	        String emoji = "";

	        if (user != null && !user.isBot()) {
	            if (emoji.equals("👍")) {
	                event.getChannel().sendMessage(user.getAsMention() + " 좋아요를 눌렀네요!").queue();
	            } else if (emoji.equals("👎")) {
	                event.getChannel().sendMessage(user.getAsMention() + " 싫어요를 눌렀네요!").queue();
	            }
	        }
	    }
	    
	    public void onMessageReactionRemove( MessageReactionRemoveEvent event) {
	    	System.out.println("test6");
	    }
	    
	    @Override
	    public void onReady(ReadyEvent event) {
	    	System.out.println("check");
	    	this.guilds = event.getJDA().getGuilds();

	        for (Guild guild : guilds) {
	            // 모든 멤버를 강제로 로드합니다. (오프라인 멤버 포함)
	        	this.memberList.put(guild, guild.loadMembers());
	        	Task<List<Member>> a = guild.loadMembers();
	            guild.loadMembers().onSuccess(members -> {
	                System.out.println(guild.getName() + " 서버의 모든 멤버 목록:");
	                
	                for (Member member : members) {
	                    System.out.println("사용자 이름: " + member.getUser().getName());
	                    System.out.println("사용자 이름: " + member.getUser().getGlobalName());
	                    System.out.println("사용자 ID: " + member.getUser().getId());
	                }
	            });
	        }
	    }
	    
	    public void getMember(Guild guild) {
	    	
//	    	memberList.get(guild).onSuccess(members ->{
//	    	for (Member member : members) {
//				System.out.println("사용자 이름: " + member.getUser().getName());
//                System.out.println("사용자 이름: " + member.getUser().getGlobalName());
//                System.out.println("사용자 ID: " + member.getUser().getId());
//			}
//	    });
	    	List<Member> members = memberList.get(guild).get();
	    	for (Member member : members) {
				System.out.println("사용자 이름: " + member.getUser().getName());
                System.out.println("사용자 이름: " + member.getUser().getGlobalName());
                System.out.println("사용자 ID: " + member.getUser().getId());
			}
	    }
	/*
	 * @Override public void onMessageReactionAdd(MessageReactionAddEvent event) {
	 * // TODO Auto-generated method stub System.out.println("이모지테스트2");
	 * super.onMessageReactionAdd(event); }
	 */
	    
	    /**
	     * Handle command without arguments.
	     *
	     * @param event
	     *        The event for this command
	     */
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

	    /**
	     * Handle command with arguments.
	     *
	     * @param event
	     *        The event for this command
	     * @param guild
	     *        The guild where its happening
	     * @param arg
	     *        The input argument
	     */
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

	    /**
	     * Inform user about successful connection.
	     *
	     * @param channel
	     *        The voice channel we connected to
	     * @param messageChannel
	     *        The text channel to send the message in
	     */
	    private void onConnecting(AudioChannel channel, MessageChannel messageChannel)
	    {
	        messageChannel.sendMessage("Connecting to " + channel.getName()).queue(); // never forget to queue()!
	    }

	    /**
	     * The channel to connect to is not known to us.
	     *
	     * @param channel
	     *        The message channel (text channel abstraction) to send failure information to
	     * @param comment
	     *        The information of this channel
	     */
	    private void onUnknownChannel(MessageChannel channel, String comment)
	    {
	        channel.sendMessage("Unable to connect to ``" + comment + "``, no such channel!").queue(); // never forget to queue()!
	    }

	    /**
	     * Connect to requested channel and start echo handler
	     *
	     * @param channel
	     *        The channel to connect to
	     */
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
