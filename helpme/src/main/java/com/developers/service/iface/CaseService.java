package com.developers.service.iface;

import java.util.List;

import com.developers.model.Case;

public interface CaseService {
	
	public void createCase(Case caso);
	
	public void updateCase(Case caso, int caseId);
	
	public void deleteCase(int caseId);
	
	public List<Case> getAll();
}
