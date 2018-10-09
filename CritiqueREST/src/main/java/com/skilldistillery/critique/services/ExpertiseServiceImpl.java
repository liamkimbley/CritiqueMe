package com.skilldistillery.critique.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.critique.entities.Expertise;
import com.skilldistillery.critique.entities.Profile;
import com.skilldistillery.critique.repositories.ExpertiseRepository;
import com.skilldistillery.critique.repositories.ProfileRepository;

@Service
public class ExpertiseServiceImpl implements ExpertiseService {

	@Autowired
	private ExpertiseRepository expRepo;

	@Autowired
	private ProfileRepository profRepo;

	@Override
	public List<Expertise> index() {
		List<Expertise> skills = expRepo.findAll();
		if (skills.isEmpty()) {
			return null;
		}
		return skills;

	}

	@Override
	public Expertise create(Expertise skill) {
		Expertise exp = new Expertise();
		if (skill != null && !skill.getTitle().equals("")) {
			exp.setTitle(skill.getTitle());
			return exp;
		}
		return null;
	}

}
