package com.lostArkRaid.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lostArkRaid.vo.UserVo;

import lombok.Setter;

@Repository
public class UserDAOImpl implements UserDAO {
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlSession;

	private static String namespace = "com.lostArkRaid.mapper.";
	private static String user = "UserMapper.";
	private static String character = "CharacterMapper.";

	@Override
	public UserVo userLogin(String userid, String userpw) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("userpw", userpw);
		System.out.println(userid+userpw);
		UserVo userVo = sqlSession.selectOne(namespace+user + "userLogin", datas);
		System.out.println(userVo);
		if(userVo==null) {
			return null;
		}
		userVo.setUserCharacterVo(sqlSession.selectOne(namespace+character + "searchUserCharacter", userVo.getUserCharacter()));
		System.out.println(userVo.getUserCharacterVo());
		return userVo; 
	}

	@Override
	public boolean checkid(String userid) {
		// TODO Auto-generated method stub
		return (Integer) sqlSession.selectOne(namespace+user + "checkId", userid) != 1;
	}

	@Override
	public boolean joinok(UserVo udto) {
		System.out.println("join_userdto : " + udto);
		return sqlSession.insert(namespace +user+ "userJoin", udto) == 1&&sqlSession.insert(namespace+character + "insertCharacter", udto.getUserCharacter()) == 1;
	}

	@Override
	public String findid(String phone) {
		return sqlSession.selectOne(namespace +user+ "findid", phone);
	}

	@Override
	public UserVo findpw(String userid) {
		return sqlSession.selectOne(namespace+user + "findpw", userid);
	}

}