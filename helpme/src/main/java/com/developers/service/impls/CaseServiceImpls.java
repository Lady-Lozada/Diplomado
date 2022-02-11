package com.developers.service.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developers.dto.CaseDTO;
import com.developers.exception.BadRequestException;
import com.developers.exception.ErrorDto;
import com.developers.exception.RestException;
import com.developers.model.Case;
import com.developers.repository.CaseRepository;
import com.developers.service.iface.ICaseService;
import com.developers.util.ConstantesUtil;

@Service
public class CaseServiceImpls implements ICaseService {

	@Autowired
	private CaseRepository caseRepo;
	
	@Override
	@Transactional
	public Case save(Case caso) throws RestException {
		if(Objects.isNull(caso)) {
			throw new BadRequestException(ErrorDto.getErrorDto(
					 HttpStatus.BAD_REQUEST.getReasonPhrase(), 
					 ConstantesUtil.MESSAGE_BAD_REQUEST, 
					 HttpStatus.BAD_REQUEST.value()));
		}
		return caseRepo.save(caso);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CaseDTO> findAll() throws RestException {
		List<Case> cases = (List<Case>) caseRepo.findAll();
		List<CaseDTO> casesDTO = new ArrayList<>();
		cases.stream().forEach(c -> {
			CaseDTO casoDTO = new CaseDTO();
			casoDTO.setIdCase(c.getIdCase());
			casoDTO.setDateHour(c.getDateHour());
			casoDTO.setAltitud(c.getAltitud());
			casoDTO.setLatitud(c.getLatitud());
			casoDTO.setLongitud(c.getLongitud());
			casoDTO.setVisible(c.getVisible());
			casoDTO.setDescriptionCase(c.getDescriptionCase());
			casoDTO.setUrlMap(c.getUrlMap());
			casoDTO.setRmiMap(c.getRmiMap());
			casoDTO.setUsuarioId(c.getUsuario().getIdUser());
			casoDTO.setImage(c.getUsuario().getImageUser());
			casoDTO.setNameUser(c.getUsuario().getFirstName());
			casoDTO.setLastNameUser(c.getUsuario().getLastName());
			casesDTO.add(casoDTO);
		});
		return casesDTO;
	}

	@Override
	@Transactional
	public Boolean visible(Boolean visible, Long id) throws RestException {
		return caseRepo.setVisible(visible, id);
	}

	@Override
	@Transactional(readOnly = true)
	public Case findById(Long id) throws RestException {
		return caseRepo.findById(id).get();
	}

	
}
