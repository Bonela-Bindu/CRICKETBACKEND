package com.dsafetech.cricket.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsafetech.cricket.entity.AdminRegistraion;
import com.dsafetech.cricket.entity.AdminValidationRequest;
import com.dsafetech.cricket.entity.Image;
import com.dsafetech.cricket.entity.TeamEntity;
import com.dsafetech.cricket.entity.UserRegister;
import com.dsafetech.cricket.entity.UserResetPasswordRequest;
import com.dsafetech.cricket.entity.UserValidationRequest;
import com.dsafetech.cricket.exceptions.UserNameAlreadyExists;
import com.dsafetech.cricket.repo.Adminrepo;
import com.dsafetech.cricket.repo.ImageRepository;
import com.dsafetech.cricket.repo.TeamRepo;
import com.dsafetech.cricket.service.AdminService;
import com.dsafetech.cricket.serviceimp.TeamServiceImpl;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/dsafetech")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private TeamRepo teamRepo;
	@Autowired
	private Adminrepo adminrepo;
	@Autowired
	private TeamServiceImpl teamserviceimpl;
	@Autowired
	private ImageRepository imageRepository;

	@ApiOperation(value = "This API is used to  register new admin", response = UserRegister.class)
	@PostMapping("/reg/admin")
	public ResponseEntity<AdminRegistraion> saveadmin(@RequestBody AdminRegistraion adminRegistraion) {
		return new ResponseEntity<AdminRegistraion>(adminService.saveadmin(adminRegistraion), HttpStatus.ACCEPTED);

	}

	@ApiOperation(value = "this API is used to  validate admin", response = UserValidationRequest.class, tags = "/admin/Validation")
	@PostMapping("/admin/Validation")
	public String validateAdmin(@RequestBody AdminValidationRequest request1) {
		String username = request1.getusername();
		String password = request1.getPassword();
		if (adminService.isValidadmin(username, password)) {
			return "Valid user";
		} else {
			return "Invalid user" + "Click on forgot password to change password";
		}
	}

	@ApiOperation(value = "This API is used to  get match from database by date")
	@GetMapping("/{date}")
	public List<TeamEntity> getteams(@PathVariable String date) {
		return teamserviceimpl.getteams(date);

	}

	@ApiOperation(value = "This API is used to reset admin password")
	@PostMapping("/admin/forgotPassword")
	public ResponseEntity<UserResetPasswordRequest> resetUserPassword(@RequestBody UserResetPasswordRequest request) {
		String username = request.getUsername();
		String gmailId = request.getGmailId();
		String newPassword = request.getNewPassword();
		AdminRegistraion adminRegistraion = adminrepo.findByUsernameAndPassword(username, newPassword);
		if (adminRegistraion != null) {
			adminRegistraion.setPassword(newPassword);
			adminRegistraion.setResetToken(null);
			adminrepo.save(adminRegistraion);

			return new ResponseEntity<UserResetPasswordRequest>(HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResetPasswordRequest>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "This API is used to  get  all match from database history")
	@GetMapping("/getAllMatches")
	public List<TeamEntity> getAllMatches(TeamEntity teamEntity) {
		return teamserviceimpl.getAllMatches();
	}

	@ApiOperation(value = "This API is used to  update team fields from database by date")
	@PutMapping("/user/update/	{contactnumber}")
	public ResponseEntity<TeamEntity> updateTeamEntity(@PathVariable long contactnumber,
			@RequestBody TeamEntity updatedTeamEntity) {
		Optional<TeamEntity> updatedTeam = teamserviceimpl.updateTeamFields(contactnumber, updatedTeamEntity);
		if (updatedTeam.isPresent()) {
			return ResponseEntity.ok(updatedTeam.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "This API is used to  store brands")
	@PostMapping("/add/brands")
    public ResponseEntity<String> uploadImage(@RequestParam long brandContactNumber, @RequestParam String  brandName,@RequestParam("file") MultipartFile file) {
		Image image1 = imageRepository.findBybrandContactNumber(brandContactNumber);
		if(image1!=null) {
			throw new UserNameAlreadyExists("BrandAlready Exists with this contact"+brandContactNumber);
		}
        try {
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setData(file.getBytes());
            image.setBrandContactNumber(brandContactNumber);
            image.setBrandName(brandName);
            imageRepository.save(image);
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }
	

	@GetMapping("/all/brands")
	 public ResponseEntity<List<Image>> getAllImages() {
	    List<Image> images = imageRepository.findAll();
	    return ResponseEntity.ok(images);
	  }
	@GetMapping("/images/{brandContactNumber}")
	public ResponseEntity<byte[]> getImageData(@PathVariable long brandContactNumber) {
	    Image image = imageRepository.findBybrandContactNumber(brandContactNumber);
	    if (image == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG); // Change to the appropriate media type if needed
	    
	    return ResponseEntity.ok().headers(headers).body(image.getData());
	}
	@PatchMapping("/update/brands/{brandContactNumber}")
    public ResponseEntity<String> updateImage(@PathVariable long brandContactNumber, @RequestBody @RequestParam("file") MultipartFile file) throws IOException {
        // Retrieve the image from the database
        Image image = imageRepository.findBybrandContactNumber(brandContactNumber);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the image properties
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());

        // Save the updated image
        imageRepository.save(image);

        return ResponseEntity.ok("Image updated successfully");
    }


}