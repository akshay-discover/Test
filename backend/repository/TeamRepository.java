package com.discover.codeorange.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.discover.codeorange.backend.entity.TeamEntity;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
	TeamEntity findByTeamName(String teamName);
}