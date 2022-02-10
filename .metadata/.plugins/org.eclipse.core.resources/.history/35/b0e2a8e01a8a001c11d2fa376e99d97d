package com.developers.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "casos")
public class Case {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCase;
	
	@Column(name = "fecha_hora")
	private LocalDateTime dateHour;
	
	private Float latitud;
	
	private Float longitud;
	
	private Float altitud;
	
	private Boolean visible;
	
	@Column(name = "descripcion")
	private String descriptionCase;

	@Column(name = "url_map")
	private String urlMap;
	
	@Column(name = "rmi_map")
	private String rmiMap;
	
	@ManyToOne
	@JoinColumn(name = "usuarios_id")
	private User usuario;
	
	@ManyToOne
	@JoinColumn(name = "delitos_id")
	private Crime crime;
	
	@PrePersist
	public void prePersist() {
		if(dateHour == null) {
			dateHour = LocalDateTime.now();
		}
	}
}
