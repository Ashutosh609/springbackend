package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.demo.models.Fitnessmodel;

public interface Fitrepo extends JpaRepository<Fitnessmodel, Long>{

	public List<Fitnessmodel> findByusername(String username);
	
}
