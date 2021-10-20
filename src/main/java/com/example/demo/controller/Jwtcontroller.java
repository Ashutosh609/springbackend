package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.AuthenticationRequest;
import com.example.demo.models.AuthenticationResponse;
import com.example.demo.services.MyUserDetailService;
import com.example.demo.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class Jwtcontroller {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailService userDetailsService;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		System.out.println(authenticationRequest);
		try {
			System.out.println("jwttryauth");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			System.out.println("jwtcatchauth");
			throw new Exception("Incorrect username or password", e);
		}
		UserDetails userDetails = this.userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		String jwt = jwtUtil.generateToken(userDetails);
		System.out.println("jwt"+jwt);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}


}
