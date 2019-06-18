package com.discover.codeorange.backend.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.discover.codeorange.backend.dto.TeamDTO;
import com.discover.codeorange.backend.dto.UserDTO;
import com.discover.codeorange.backend.entity.UserEntity;
import com.discover.codeorange.backend.exception.UserServiceException;
import com.discover.codeorange.backend.repository.UserRepository;
import com.discover.codeorange.backend.response.ErrorMessages;
import com.discover.codeorange.backend.response.UserRest;
import com.discover.codeorange.backend.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDTO createUser(UserDTO user) {

//		UserEntity userEntity = new UserEntity();
//		BeanUtils.copyProperties(user, userEntity);
		
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("Record already exists");
		
		for(int i = 0; i < user.getTeams().size(); i++) {
			TeamDTO team = user.getTeams().get(i);
			team.setUserDetails(user);
			user.getTeams().set(i, team);
		}
		
		//Move next 2 lines to update later --maybe
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity storedUserDetails = userRepository.save(userEntity);

//		UserDTO returnValue = new UserDTO();
//		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		//Move next 2 lines to update later --maybe
		UserDTO returnValue = modelMapper.map(storedUserDetails, UserDTO.class);

		return returnValue;
	
	}

	@Override
	public UserDTO getUser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
		
	}

	// For logging in
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	
	}

	@Override
	public UserDTO getUserByUsername(String username) {

		UserDTO returnValue = new UserDTO();
		UserEntity userEntity = userRepository.findByUsername(username);

		if (userEntity == null)
			throw new UsernameNotFoundException(username + " NOT FOUND");

		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	
	}

	@Override
	public UserDTO updateUser(String username, UserDTO user) {

		UserDTO returnValue = new UserDTO();
		UserEntity userEntity = userRepository.findByUsername(username);

		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());

		UserEntity updatedUser = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUser, returnValue);

		return returnValue;
	
	}

	@Override
	public void deleteUser(String username) {
		
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDTO> getUsers(int page, int limit) {
		
		List<UserDTO> returnValue = new ArrayList<>();
		
		if (page>0) page -= 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();
		
		for (UserEntity userEntity : users) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(userEntity, userDTO);
			returnValue.add(userDTO);
		}
		
		return returnValue;
	
	}
}