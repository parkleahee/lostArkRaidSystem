package com.lostArkRaid.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.lostArkRaid.util.ContextUtil;
import com.lostArkRaid.vo.RaidSchedulerVO;

import lombok.Setter;

public class DiscordDAOImpl implements DiscordDAO{
	
	private SqlSession sqlSession;
	
	public DiscordDAOImpl(SqlSession sqlSession) {
		 if(this.sqlSession == null) {
             this.sqlSession = sqlSession;
          }
	}
	
	public DiscordDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static String namespace = "com.lostArkRaid.mapper.";
	private static String user = "UserMapper.";
	private static String character = "CharacterMapper.";
	
	@Override
	public String getApikey(String name) {
		// TODO Auto-generated method stub
		System.out.println(name);
		System.out.println(sqlSession);
		String api = sqlSession.selectOne(namespace+user + "getApikey", name);
		System.out.println(api);
		return api;
	}
	
	@Override
	public List<RaidSchedulerVO> getSchedule() {
		// TODO Auto-generated method stub
		return null;
	}
}
