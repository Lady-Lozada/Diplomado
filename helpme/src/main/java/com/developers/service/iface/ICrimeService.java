package com.developers.service.iface;

import java.util.List;

import com.developers.dto.CrimeDTO;
import com.developers.exception.NotFoundException;
import com.developers.model.Crime;

public interface ICrimeService {
	
	public List<CrimeDTO> findAll();
	
	public Crime findById(Long id);
	
	public Crime save(Crime crime);
	
	public void delete(Long id) throws NotFoundException;
}
