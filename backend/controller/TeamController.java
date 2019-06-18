package com.discover.codeorange.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.discover.codeorange.backend.dto.TeamDTO;
import com.discover.codeorange.backend.request.TeamDetails;
import com.discover.codeorange.backend.response.TeamRest;
import com.discover.codeorange.backend.service.TeamService;

@RestController
@RequestMapping("teams") // http://localhost:8080/teams
public class TeamController {

	@Autowired
	TeamService teamService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public TeamRest createTeam(@RequestBody TeamDetails teamDetails) {
		TeamRest returnValue = new TeamRest();
		TeamDTO teamDto = new TeamDTO();
		BeanUtils.copyProperties(teamDetails, teamDto);

		TeamDTO createdTeam = teamService.createTeam(teamDto);
		BeanUtils.copyProperties(createdTeam, returnValue);

		return returnValue;
	}

}