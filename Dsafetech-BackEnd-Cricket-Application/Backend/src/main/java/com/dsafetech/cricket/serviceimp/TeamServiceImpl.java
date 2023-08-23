package com.dsafetech.cricket.serviceimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsafetech.cricket.entity.TeamEntity;
import com.dsafetech.cricket.exceptions.MaxPlayersReachedException;
import com.dsafetech.cricket.exceptions.TeamAlreadyExsists;
import com.dsafetech.cricket.repo.TeamRepo;
import com.dsafetech.cricket.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	private static final int Max_Players = 14;
	@Autowired
	private TeamRepo teamRepo;

	@Override
	public TeamEntity save(TeamEntity teamEntity) {
		TeamEntity existingteam = teamRepo.findByteamName(teamEntity.getTeamName());
		if (existingteam != null) {
			throw new TeamAlreadyExsists("Your Team Already Exists: with " + teamEntity.getTeamName()
					+ "Your team Registered " + teamEntity.getEmailId());
		}
	if (teamEntity.getTeamplayers().size() > Max_Players) {
		throw new MaxPlayersReachedException("Players limit exceed (10)");
		}
		return teamRepo.save(teamEntity);
	}

	@Override
	public List<TeamEntity> getteams(String date) {
		return teamRepo.findByDate(date);
	}

	@Override
	public List<TeamEntity> getAllMatches() {
		return teamRepo.findAll();
	}


	@Override
	public Optional<TeamEntity> updateTeamFields(long contactnumber, TeamEntity updatedTeamEntity) {
		Optional<TeamEntity> existingTeamEntity = teamRepo.findByContactnumber(updatedTeamEntity.getContactnumber());
		if (existingTeamEntity.isPresent()) {
            TeamEntity teamEntity = existingTeamEntity.get();
            BeanUtils.copyProperties(updatedTeamEntity, teamEntity, "teamName");
            return Optional.of(teamRepo.save(teamEntity));
        }
        return Optional.empty();
    }

}
