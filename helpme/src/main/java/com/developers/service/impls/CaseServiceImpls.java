package com.developers.service.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developers.model.Case;
import com.developers.repository.CaseRepository;
import com.developers.service.iface.CaseService;

@Service
public class CaseServiceImpls implements CaseService {

	@Autowired
	private CaseRepository caseRepository;
	
	@Override
	public void createCase(Case caso) {
//		boolean existCase = caseRepository.existsById(caso.getIdCase());
//		if(!existCase) {
//			caseRepository.save(caso);
//		}else {
//			System.out.println("El caso ya existe!");
//		}
	}

	@Override
	public void updateCase(Case caso, int caseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCase(int caseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Case> getAll() {
		List<Case> casos = new ArrayList<>();
		casos = (List<Case>) caseRepository.findAll();
		return casos;
	}

	
}
