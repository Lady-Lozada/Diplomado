package com.developers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developers.model.Case;
import com.developers.service.iface.CaseService;

@RestController
@RequestMapping("/casos")
@CrossOrigin("*")
public class CaseController {
	
	@Autowired
	private CaseService caseService;
	
	@GetMapping("/all")
	public List<Case> getAll() {
		return caseService.getAll();
	}
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createCase(@RequestBody Case caso) {
		caseService.createCase(caso);
	}
}
