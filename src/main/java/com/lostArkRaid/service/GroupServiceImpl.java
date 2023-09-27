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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lostArkRaid.dao.GroupDAO;
import com.lostArkRaid.dao.GroupDAOImpl;
import com.lostArkRaid.dao.UserDAO;
import com.lostArkRaid.dao.UserDAOImpl;
import com.lostArkRaid.util.ConvertUtils;
import com.lostArkRaid.vo.CharacterVo;

import lombok.Setter;

@Service
public class GroupServiceImpl implements GroupService{

	@Setter(onMethod_ = @Autowired)
	GroupDAO gdao = new GroupDAOImpl();
	
	@Override
	public boolean checkGroupName(String groupName, String userid) {
		return false;
	}


	
}
