package com.developers.model;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(name = "enabled", unique = true, length = 120)
	private Boolean enabledUser;
	
	private String password;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "nombre")
	private String firstName;
	
	@Column(name = "apellido")
	private String lastName;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate dateBirth;
	
	@Column(name = "red_social")
	private int socialNetwork;
	
	@Column(name = "image")
	private Blob imageUser;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roles_usuarios", 
	joinColumns = {@JoinColumn(name="usuarios_id")},
	inverseJoinColumns = {@JoinColumn(name = "roles_id")})
	private List<Role> roles;
}
