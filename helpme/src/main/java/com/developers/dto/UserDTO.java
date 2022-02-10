package com.developers.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;

	private String username;
	
	private String nombre;

	private String apellido;
	
	private LocalDate fechaNacimiento;
	
	private Boolean enabled;
	
	private String image;
	
	private List<String> roles;
}
