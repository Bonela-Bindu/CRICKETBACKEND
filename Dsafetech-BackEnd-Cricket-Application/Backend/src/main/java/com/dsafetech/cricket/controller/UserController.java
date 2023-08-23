
package com.dsafetech.cricket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsafetech.cricket.entity.TeamEntity;
import com.dsafetech.cricket.entity.UserRegister;
import com.dsafetech.cricket.entity.UserResetPasswordRequest;
import com.dsafetech.cricket.entity.UserValidationRequest;
import com.dsafetech.cricket.repo.UserRepo;
import com.dsafetech.cricket.service.Userservice;
import com.dsafetech.cricket.serviceimp.TeamServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/dsafetech")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private TeamServiceImpl teamserviceimpl;
	
	@Autowired
	private UserRepo userreRepo;
	@Autowired
	private Userservice userservice;
	

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@ApiOperation(value = "This API is used to  register new user", response = UserRegister.class)
	@PostMapping("/reg/user")
	public ResponseEntity<UserRegister> save(@RequestBody UserRegister userRegister) {
		return new ResponseEntity<UserRegister>(userservice.save1(userRegister), HttpStatus.ACCEPTED);

	}


	@ApiOperation(value = "This API is used to book ground with team players", response = TeamEntity.class)
	@PostMapping("/reg/match")
	public ResponseEntity<TeamEntity> save(@RequestBody TeamEntity teamEntity) {
		TeamEntity creatTeamEntity = teamserviceimpl.save(teamEntity);
		return new ResponseEntity<>(creatTeamEntity, HttpStatus.CREATED);
	}

	

	@ApiOperation(value = "this API is used to  validate user", response = UserValidationRequest.class, tags = "/Validation")
	@PostMapping("/user/Validation")
	public String validateUser(@RequestBody UserValidationRequest request) {
		String username = request.getusername();
		String password = request.getPassword();
		if (userservice.isValidUser(username, password)) {
			return "Valid user";
		} else {
			return "Invalid user";
		}

	}



	@ApiOperation(value = "This API is used to reset user password")
	@PostMapping("/user/forgotPassword")
	public ResponseEntity<UserResetPasswordRequest> resetUserPassword(@RequestBody UserResetPasswordRequest request) {
		String username = request.getUsername();
		String gmailId = request.getGmailId();
		String newPassword = request.getNewPassword();
		UserRegister userRegister = userreRepo.findByUsernameAndGmailId(username, gmailId);
		
		if (userRegister != null) {
			userRegister.setPassword(newPassword);
			userRegister.setResetToken(null);
			userreRepo.save(userRegister);

			return new ResponseEntity<UserResetPasswordRequest>(HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResetPasswordRequest>(HttpStatus.BAD_REQUEST);
		}
	
	}
	


	@GetMapping("/reg/user")
	public ResponseEntity<List<UserRegister> > getList(){
		List<UserRegister> user=userservice.getList();
		if(user.size()>0) {
			return ResponseEntity.of(Optional.of(user));
	}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
