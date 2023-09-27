package com.lostArkRaid.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lostArkRaid.vo.UserVo;

import lombok.Setter;

@Repository
public class GroupDAOImpl implements GroupDAO {
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlSession;

	private static String namespace = "com.lostArkRaid.mapper.";
	private static String user = "UserMapper.";
	private static String character = "CharacterMapper.";
	private static String group = "GroupMapper.";
	
	
	@Override
	public boolean checkGroupName(String groupName, String userid) {
		HashMap<String,String> data = new HashMap<String, String>();
		data.put("groupName", groupName);
		data.put("userid", userid);
		return (Integer)sqlSession.selectOne(namespace+group+"checkGroupName",data)!=1;
	}

	

}