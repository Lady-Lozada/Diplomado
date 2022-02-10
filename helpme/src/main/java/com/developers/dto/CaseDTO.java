package com.developers.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CaseDTO {
	
	private Long idCase;
	private String nameUser;
	private String lastNameUser;
	private LocalDateTime dateHour;
	private Float latitud;
	private Float longitud;
	private Float altitud;
	private Boolean visible;
	private String descriptionCase;
	private String urlMap;
	private String rmiMap;
	private Long usuarioId;
	private String image;
}
