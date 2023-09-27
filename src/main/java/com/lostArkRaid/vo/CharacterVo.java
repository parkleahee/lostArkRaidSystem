package com.lostArkRaid.vo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
public class CharacterVo {

	private String characterImage;
	private String expeditionLevel;
	private String pvpGradeName;
	private String townLevel;
	private String townName;
	private String title;
	private String guildMemberGrade;
	private String guildName;
	private String usingSkillPoint;
	private String totalSkillPoint;
	private String serverName;
	private String characterName;
	private String characterLevel;
	private String characterClassName;
	private String itemAvgLevel;
	private String itemMaxLevel;
	
	public CharacterVo() {
		// TODO Auto-generated constructor stub
	}
	
	public void setCharacterVo(Map<String, Object> data) {
		this.characterImage = data.get("CharacterImage")+"";
		this.expeditionLevel = data.get("ExpeditionLevel")+"";
		this.pvpGradeName = data.get("PvpGradeName")+"";
		this.townLevel = data.get("TownLevel")+"";
		this.townName = data.get("TownName")+"";
		this.title = data.get("Title")+"";
		this.guildMemberGrade = data.get("GuildMemberGrade")+"";
		this.guildName = data.get("GuildName")+"";
		this.usingSkillPoint = data.get("UsingSkillPoint")+"";
		this.totalSkillPoint = data.get("TotalSkillPoint")+"";
		this.serverName = data.get("ServerName")+"";
		this.characterName = data.get("CharacterName")+"";
		this.characterLevel = data.get("CharacterLevel")+"";
		this.characterClassName = data.get("CharacterClassName")+"";
		this.itemAvgLevel = data.get("ItemAvgLevel")+"";
		this.itemMaxLevel = data.get("ItemMaxLevel")+"";
		
	}
	
	
	
	
	
	public CharacterVo(String characterImage, String expeditionLevel, String pvpGradeName, String townLevel,
			String townName, String title, String guildMemberGrade, String guildName, String usingSkillPoint,
			String totalSkillPoint, String serverName, String characterName, String characterLevel,
			String characterClassName, String itemAvgLevel, String itemMaxLevel) {
		super();
		this.characterImage = characterImage;
		this.expeditionLevel = expeditionLevel;
		this.pvpGradeName = pvpGradeName;
		this.townLevel = townLevel;
		this.townName = townName;
		this.title = title;
		this.guildMemberGrade = guildMemberGrade;
		this.guildName = guildName;
		this.usingSkillPoint = usingSkillPoint;
		this.totalSkillPoint = totalSkillPoint;
		this.serverName = serverName;
		this.characterName = characterName;
		this.characterLevel = characterLevel;
		this.characterClassName = characterClassName;
		this.itemAvgLevel = itemAvgLevel;
		this.itemMaxLevel = itemMaxLevel;
	}



	public String getCharacterImage() {
		return characterImage;
	}




	public void setCharacterImage(String characterImage) {
		this.characterImage = characterImage;
	}




	public String getExpeditionLevel() {
		return expeditionLevel;
	}




	public void setExpeditionLevel(String expeditionLevel) {
		this.expeditionLevel = expeditionLevel;
	}




	public String getPvpGradeName() {
		return pvpGradeName;
	}




	public void setPvpGradeName(String pvpGradeName) {
		this.pvpGradeName = pvpGradeName;
	}




	public String getTownLevel() {
		return townLevel;
	}




	public void setTownLevel(String townLevel) {
		this.townLevel = townLevel;
	}




	public String getTownName() {
		return townName;
	}




	public void setTownName(String townName) {
		this.townName = townName;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getGuildMemberGrade() {
		return guildMemberGrade;
	}




	public void setGuildMemberGrade(String guildMemberGrade) {
		this.guildMemberGrade = guildMemberGrade;
	}




	public String getGuildName() {
		return guildName;
	}




	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}




	public String getUsingSkillPoint() {
		return usingSkillPoint;
	}




	public void setUsingSkillPoint(String usingSkillPoint) {
		this.usingSkillPoint = usingSkillPoint;
	}




	public String getTotalSkillPoint() {
		return totalSkillPoint;
	}




	public void setTotalSkillPoint(String totalSkillPoint) {
		this.totalSkillPoint = totalSkillPoint;
	}




	public String getServerName() {
		return serverName;
	}




	public void setServerName(String serverName) {
		this.serverName = serverName;
	}




	public String getCharacterName() {
		return characterName;
	}




	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}




	public String getCharacterLevel() {
		return characterLevel;
	}




	public void setCharacterLevel(String characterLevel) {
		this.characterLevel = characterLevel;
	}




	public String getCharacterClassName() {
		return characterClassName;
	}




	public void setCharacterClassName(String characterClassName) {
		this.characterClassName = characterClassName;
	}




	public String getItemAvgLevel() {
		return itemAvgLevel;
	}




	public void setItemAvgLevel(String itemAvgLevel) {
		this.itemAvgLevel = itemAvgLevel;
	}




	public String getItemMaxLevel() {
		return itemMaxLevel;
	}




	public void setItemMaxLevel(String itemMaxLevel) {
		this.itemMaxLevel = itemMaxLevel;
	}




	@Override
	public String toString() {
		return "CharacterVo [characterImage=" + characterImage + ", expeditionLevel=" + expeditionLevel
				+ ", pvpGradeName=" + pvpGradeName + ", townLevel=" + townLevel + ", townName=" + townName + ", title="
				+ title + ", guildMemberGrade=" + guildMemberGrade + ", guildName=" + guildName + ", usingSkillPoint="
				+ usingSkillPoint + ", totalSkillPoint=" + totalSkillPoint + ", serverName=" + serverName
				+ ", characterName=" + characterName + ", characterLevel=" + characterLevel + ", characterClassName="
				+ characterClassName + ", itemAvgLevel=" + itemAvgLevel + ", itemMaxLevel=" + itemMaxLevel + "]";
	}
	
	




	
	
	
}
