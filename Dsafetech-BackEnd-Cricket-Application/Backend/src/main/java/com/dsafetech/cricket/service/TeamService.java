package com.dsafetech.cricket.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dsafetech.cricket.entity.TeamEntity;
import com.dsafetech.cricket.entity.UserRegister;

public interface TeamService {

	public TeamEntity save(TeamEntity teamEntity);
	public List<TeamEntity> getteams(String date);
	public List<TeamEntity> getAllMatches();
	public Optional<TeamEntity> updateTeamFields(long contactnumber, TeamEntity teamEntity);

}
