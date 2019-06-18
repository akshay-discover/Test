package com.discover.codeorange.backend.repository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.discover.codeorange.backend.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> {
	
	UserEntity findByEmail(String email);

	UserEntity findByUsername(String username);
	
}