package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.*;

import com.example.demo.models.AuthenticationResponse;
import com.example.demo.models.Fitnessmodel;
import com.example.demo.repo.Fitrepo;
import com.example.demo.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class Fitcontroller {
	
	@Autowired
	Fitrepo fitrepo;
	
	@Autowired 
	private JwtUtil jwtutil;
	
	@PostMapping("/register")
	public String insertdata(@RequestBody Fitnessmodel fitnessmodel) {
		try {
		System.out.println(fitnessmodel.getEmail());
		fitrepo.save(fitnessmodel);
		return "data saved successfully";
		}catch(Exception e) {
			return "data not saved successfully";
		}
	}
	
	
	@GetMapping("/signout")
	public String signout() {
		return "Logoff successfully";
	}
	
	@PostMapping("/auth")
	public String Authenticate(@RequestBody AuthenticationResponse data) {
		try {
			String username=jwtutil.extractUsername(data.getJwt());
			List<Fitnessmodel> fr=fitrepo.findByusername(username);
		return fr.get(0).getUsername();}
		catch (Exception e) {
			return "NotVerified successfully";
		}
	}
}
