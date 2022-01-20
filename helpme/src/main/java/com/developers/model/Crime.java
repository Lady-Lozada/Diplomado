package com.developers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "delitos")
public class Crime {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCrime;
	
	@Column(name = "nombre")
	private String nameCrime;
	
	@Column(name = "descripcion")
	private String descriptionCrime;
	
	@ManyToOne
	@JoinColumn(name = "usuarios_id")
	private User usuario;	
}
