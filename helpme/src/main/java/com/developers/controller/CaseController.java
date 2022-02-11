package com.developers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developers.dto.CaseDTO;
import com.developers.exception.RestException;
import com.developers.model.Case;
import com.developers.service.iface.ICaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/cases")
@Api(value = "/cases", tags = {"Cases"})
@SwaggerDefinition(tags = {
		@Tag(name = "Cases", description = "Gestión API Casos")
})
@CrossOrigin("*")
public class CaseController {
	
	@Autowired
	private ICaseService caseService;
	
	@ApiOperation(value = "Obtiene una lista de todos los casos", response = CaseDTO.class, responseContainer = "List",
			produces = "application/json", httpMethod = "GET")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<CaseDTO>> index() throws RestException{
		List<CaseDTO> caseDTO = caseService.findAll();
		return ResponseEntity.ok().body(caseDTO);
	}
	
	@ApiOperation(value = "Crear un caso", response = Case.class,
			produces = "application/json", httpMethod = "POST")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Case> create(@RequestBody Case caso) throws RestException{
		Case caseSaved = caseService.save(caso);
		return ResponseEntity.status(HttpStatus.CREATED).body(caseSaved);
	}
}
