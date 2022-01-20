package com.developers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;
	
	@Column(name = "nombre", nullable = false, length = 45)
	private String nameRole;
	
	@Column(name = "descripcion", length = 45)
	private String descriptionRole;
	
	// OPcional
//	@ManyToMany(mappedBy = "roles")
//	private List<User> usuarios;
	
}
