package com.skilldistillery.critique.services;

import java.util.List;

import com.skilldistillery.critique.entities.Expertise;

public interface ExpertiseService {

	public List<Expertise> index();
	public Expertise create(Expertise skill);
	public Expertise findOneById(Integer id);
	public Expertise findOneByTitle(String title);
}
