package com.developers.service.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developers.dto.CrimeDTO;
import com.developers.model.Crime;
import com.developers.repository.CrimeRepository;
import com.developers.service.iface.ICrimeService;

@Service
public class CrimeServiceImpl implements ICrimeService {
	
	@Autowired
	private CrimeRepository crimeRepo;

	@Override
	@Transactional(readOnly = true) // readOnly se usa unicamente para solo lectura ya que es una consulta
	public List<CrimeDTO> findAll() {
		List<Crime> crimes = (List<Crime>) crimeRepo.findAll();
		List<CrimeDTO> crimesDto = new ArrayList<CrimeDTO>();
		crimes.stream().forEach(cr -> {
			CrimeDTO crimeDto = new CrimeDTO();
			crimeDto.setId(cr.getIdCrime());
			crimeDto.setName(cr.getNameCrime());
			crimeDto.setDescription(cr.getDescriptionCrime());
			crimesDto.add(crimeDto);
		});
		return null;
	}

	@Override
	public CrimeDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrimeDTO save(CrimeDTO crime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
