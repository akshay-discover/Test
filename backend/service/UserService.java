package com.discover.codeorange.backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.discover.codeorange.backend.dto.UserDTO;
import com.discover.codeorange.backend.response.UserRest;

public interface UserService extends UserDetailsService {
	
	UserDTO createUser(UserDTO user);

	UserDTO updateUser(String username, UserDTO user);
	
	void deleteUser(String username);

	UserDTO getUser(String email);

	UserDTO getUserByUsername(String username);
	
	List<UserDTO> getUsers(int page, int limit);
	
}