package com.developers.service.iface;

import java.util.List;

import com.developers.dto.UserDTO;
import com.developers.exception.RestException;
import com.developers.model.User;

public interface IUserService {
	/*
	 * Listar todos los usuarios
	 */
	public List<UserDTO> listUsers() throws RestException;
	
	public User listUser(Long id) throws RestException;
	
	public User saveUser(User usuario) throws RestException;
	
	User listByUsername(String userName) throws RestException;

	User saveUserImage(User usuario) throws RestException;

	User updateUser(User usuario) throws RestException;
}
