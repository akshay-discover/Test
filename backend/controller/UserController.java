package com.discover.codeorange.backend.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.discover.codeorange.backend.dto.UserDTO;
import com.discover.codeorange.backend.exception.UserServiceException;
import com.discover.codeorange.backend.request.UserDetails;
import com.discover.codeorange.backend.response.ErrorMessages;
import com.discover.codeorange.backend.response.OperationStatusModel;
import com.discover.codeorange.backend.response.OperationName;
import com.discover.codeorange.backend.response.OperationStatus;
import com.discover.codeorange.backend.response.UserRest;
import com.discover.codeorange.backend.service.UserService;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{username}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String username) {
		
		UserRest returnValue = new UserRest();
		UserDTO userDTO = userService.getUserByUsername(username);
		
		BeanUtils.copyProperties(userDTO, returnValue);

		return returnValue;
		
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers( @RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="25") int limit) {
		
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDTO> users = userService.getUsers(page, limit);
		
		for (UserDTO userDTO : users) {
			UserRest user = new UserRest();
			BeanUtils.copyProperties(userDTO, user);
			returnValue.add(user);
		}
		
		return returnValue;
		
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetails userDetails) throws Exception {

		UserRest returnValue = new UserRest();

//		if (userDetails.getFirstName().isEmpty())
//			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

//		UserDTO userDto = new UserDTO();
//		BeanUtils.copyProperties(userDetails, userDto);
		
		//Move next 2 lines to Put later --maybe
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDto = modelMapper.map(userDetails, UserDTO.class);

		UserDTO createdUser = userService.createUser(userDto);
//		BeanUtils.copyProperties(createdUser, returnValue);
		
		//Move next line to Put later --maybe
		returnValue = modelMapper.map(createdUser, UserRest.class);

		return returnValue;
	
	}

	@PutMapping(path = "/{username}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String username, @RequestBody UserDetails userDetails) {
		
		UserRest returnValue = new UserRest();

		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDTO updatedUser = userService.updateUser(username, userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);

		return returnValue;
	
	}
	
	@DeleteMapping(path = "/{username}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String username, @RequestBody UserDetails userDetails) {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		returnValue.setOperationName(OperationName.DELETE.name());
		userService.deleteUser(username);
		returnValue.setOperationResult(OperationStatus.SUCCESS.name());
		
		return returnValue;
		
	}

}