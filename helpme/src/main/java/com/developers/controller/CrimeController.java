package com.developers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developers.dto.CrimeDTO;
import com.developers.service.iface.ICrimeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/crimes")
@Api(value = "/crimes", tags = {"Crimes"})
@SwaggerDefinition(tags = {
		@Tag(name = "Crimes", description = "Gestión API Delitos")
})
public class CrimeController {

	private static final Logger log = LoggerFactory.getLogger(CrimeController.class);
	
	@Autowired
	private ICrimeService crimeService;
	
	@ApiOperation(value = "Obtiene una lista de todos los delitos", response = CrimeDTO.class, responseContainer = "List",
			produces = "application/json", httpMethod = "GET")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CrimeDTO> index(){
		log.info("Inicio metodo index controller");
		return crimeService.findAll();		
	}
}