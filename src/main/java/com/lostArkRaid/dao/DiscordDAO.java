package com.lostArkRaid.dao;

import java.util.List;

import com.lostArkRaid.vo.RaidSchedulerVO;

public interface DiscordDAO {
	public String getApikey(String name);

	public List<RaidSchedulerVO> getSchedule();
}
