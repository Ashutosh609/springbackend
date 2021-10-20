package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.Fitnessmodel;
import com.example.demo.repo.Fitrepo;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	Fitrepo fitrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Fitnessmodel> fr=fitrepo.findByusername(username);
		if (username.equals(fr.get(0).getUsername())){
		return new User(fr.get(0).getUsername(),fr.get(0).getPassword(), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("Invalid data");
		}
}
	
}
