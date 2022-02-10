package com.developers.service.iface;

import java.util.List;

import com.developers.dto.CaseDTO;
import com.developers.exception.RestException;
import com.developers.model.Case;

public interface ICaseService {
	
	Case save(Case caso) throws RestException;
	
	List<CaseDTO> findAll() throws RestException;
	
	Boolean visible(Boolean visible, Long id) throws RestException;
	
	Case findById(Long id) throws RestException;
}
