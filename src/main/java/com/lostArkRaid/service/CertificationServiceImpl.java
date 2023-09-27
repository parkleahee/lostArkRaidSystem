package com.lostArkRaid.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lostArkRaid.util.ConvertUtils;
import com.lostArkRaid.vo.CharacterVo;

@Service
public class CertificationServiceImpl implements CertificationService{

	HttpClient client = HttpClient.newHttpClient();
//	String 
	@Override
	public CharacterVo searchCharacter(String apikey, String characterName) {
		
	//	String CharacterName = "둘리와마법";
		System.out.println("체크"+characterName+apikey);
		Gson gson = new Gson();
		characterName = URLEncoder.encode(characterName);
		HttpRequest request = HttpRequest.newBuilder()
//		    .uri(URI.create("https://developer-lostark.game.onstove.com/armories/characters/coolguy/profiles"))
//		    .uri(URI.create("https://developer-lostark.game.onstove.com/characters/%EB%91%98%EB%A6%AC%EC%99%80%EB%A7%88%EB%B2%95/siblings")) //전체 캐릭터 불러오기
		    .uri(URI.create("https://developer-lostark.game.onstove.com/armories/characters/"+characterName+"/profiles")) //캐릭터 하나 불러오기
			.headers("Origin", "*") // 모든 도메인에서 요청을 허용하도록 설정
	        .headers("accept", "application/json") 
	        .headers("authorization", "bearer "+apikey) 
		    .GET()
		    .build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
				String body = response.body();
				JSONObject jb= (JSONObject)new JSONParser().parse(body);
				Map<String, Object> data = jb;
				ObjectMapper mapper = new ObjectMapper();
				CharacterVo characterVo = new CharacterVo(); 
				characterVo.setCharacterVo(data);
				//CharacterVo characterVo = ConvertUtils.convertToValueObject(data, CharacterVo.class);
				return characterVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

	@Override
	public ArrayList<CharacterVo> searchAllCharacter(String apikey, String characterName) {
		characterName = URLEncoder.encode(characterName);
		ArrayList<CharacterVo> characterList = new ArrayList<CharacterVo>();
		HttpRequest request = HttpRequest.newBuilder()
//		    .uri(URI.create("https://developer-lostark.game.onstove.com/armories/characters/coolguy/profiles"))
		    .uri(URI.create("https://developer-lostark.game.onstove.com/characters/"+characterName+"/siblings")) //전체 캐릭터 불러오기
//		    .uri(URI.create("https://developer-lostark.game.onstove.com/armories/characters/"+CharacterName+"/profiles")) //캐릭터 하나 불러오기
			.headers("Origin", "*") // 모든 도메인에서 요청을 허용하도록 설정
	        .headers("accept", "application/json") 
	        .headers("authorization", "bearer "+apikey) 
		    .GET()
		    .build();
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			Gson gson = new Gson();
			System.out.println(response.body());
				String body = response.body();
//				JSONObject jb= (JSONObject)new JSONParser().parse(body);
				JSONArray ja = (JSONArray)new JSONParser().parse(body);
//				Map<String, Object> data = jb;
				for (int i = 0; i < ja.size(); i++) {
					JSONObject jb = (JSONObject) ja.get(i);
					System.out.println(jb);
					Map<String, Object> data = jb;
					System.out.println(data.get("CharacterName"));
					CharacterVo characterVo = new CharacterVo(); 
					characterVo.setCharacterVo(data);
					System.out.println(characterVo);
				//	Map<String, Object> data = jb;
				//	CharacterVo characterVo = ConvertUtils.convertToValueObject(data, CharacterVo.class);
					characterList.add(characterVo);
				//	System.out.println(characterVo);
				}
				return characterList;
//				CharacterVo a = ConvertUtils.convertToValueObject(data, CharacterVo.class);
//				System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
}
