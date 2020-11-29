package com.edu.security.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.security.dto.JwtResponse;
import com.edu.security.dto.LoginRequest;
import com.edu.security.dto.SignupRequest;
import com.edu.security.entities.ERole;
import com.edu.security.entities.Role;
import com.edu.security.entities.User;
import com.edu.security.jwt.JwtUtils;
import com.edu.security.repositories.RoleRepository;
import com.edu.security.repositories.UserRepository;
import com.edu.security.services.impl.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public String authenticateUser(@ModelAttribute LoginRequest loginRequest, Model model) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		/*
		 * List<String> roles = userDetails.getAuthorities().stream().map(item ->
		 * item.getAuthority()) .collect(Collectors.toList());
		 * 
		 * return ResponseEntity.ok( new JwtResponse(jwt, userDetails.getId(),
		 * userDetails.getUsername(), userDetails.getEmail(), roles));
		 * 
		 */
		model.addAttribute("name", userDetails.getUsername());
		return "homepage";
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute SignupRequest signUpRequest, Model model) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			model.addAttribute("message", "Username is used");
			return "index";
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			model.addAttribute("message", "Email is used");
			return "index";
		}
		if (!signUpRequest.getPassword().equals(signUpRequest.getRepassword())) {
			return "index";
		}
		
		String strRoles = signUpRequest.getRole();
		if(strRoles.equals("")) {
			model.addAttribute("message", "Please check role");
			return "index";
		}
		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), 1);

		Set<Role> roles = new HashSet<Role>();
		switch (strRoles) {
		case "admin":
			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(adminRole);

			break;
		case "mod":
			Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(modRole);

			break;
		default:
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}
		user.setRoles(roles);
		userRepository.save(user);
		return "redirect:/login";
	}
}