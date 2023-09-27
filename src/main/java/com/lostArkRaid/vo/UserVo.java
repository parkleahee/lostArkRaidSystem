package com.lostArkRaid.vo;

public class UserVo {
	private String userid;
    private String userpw;
    private String username;
    private String userApiKey;
    private String userCharacter;
    private CharacterVo userCharacterVo;
    
    public UserVo() {
		// TODO Auto-generated constructor stub
	}
    
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserApiKey() {
		return userApiKey;
	}
	public void setUserApiKey(String userApiKey) {
		this.userApiKey = userApiKey;
	}
	public String getUserCharacter() {
		return userCharacter;
	}
	public void setUserCharacter(String userCharacter) {
		this.userCharacter = userCharacter;
	}
	public CharacterVo getUserCharacterVo() {
		return userCharacterVo;
	}
	public void setUserCharacterVo(CharacterVo userCharacterVo) {
		this.userCharacterVo = userCharacterVo;
	}

	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", userApiKey="
				+ userApiKey + ", userCharacter=" + userCharacter + ", userCharacterVo=" + userCharacterVo + "]";
	}
    
}
