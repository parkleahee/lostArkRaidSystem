package com.lostArkRaid.service;

import java.util.ArrayList;

import com.lostArkRaid.vo.CharacterVo;

public interface CertificationService {
	
	public CharacterVo searchCharacter(String apikey, String characterName);
	public ArrayList<CharacterVo> searchAllCharacter(String apikey, String characterName);
	
}
