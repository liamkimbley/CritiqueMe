package com.skilldistillery.critique.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Expertise;
import com.skilldistillery.critique.repositories.ExpertiseRepository;

@Service
public class ExpertiseServiceImpl implements ExpertiseService {

	@Autowired
	private ExpertiseRepository expRepo;
	
	@Override
	public List<Expertise> index() {
		List<Expertise> skills = expRepo.findAll();
		if (skills.isEmpty()) {
			return null;
		}
		return skills;
		
	}

}
