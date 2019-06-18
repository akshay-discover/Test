package com.discover.codeorange.backend.serviceImplementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.discover.codeorange.backend.dto.TeamDTO;
import com.discover.codeorange.backend.entity.TeamEntity;
import com.discover.codeorange.backend.repository.TeamRepository;
import com.discover.codeorange.backend.service.TeamService;

@Service
public class TeamServiceImplementation implements TeamService {

	@Autowired
	TeamRepository teamRepository;

	@Override
	public TeamDTO createTeam(TeamDTO team) {

		if (teamRepository.findByTeamName(team.getTeamName()) != null)
			throw new RuntimeException("Record Already Exists");

		TeamEntity teamEntity = new TeamEntity();
		BeanUtils.copyProperties(team, teamEntity);

		teamEntity.setTeamID(6);
		// teamEntity.setTeamName("Team Trident");
		// teamEntity.setDepartmentName("Business Technology");

		TeamEntity storedTeamDetails = teamRepository.save(teamEntity);

		TeamDTO returnValue = new TeamDTO();
		BeanUtils.copyProperties(storedTeamDetails, returnValue);

		return returnValue;
	}
}