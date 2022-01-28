package com.developers.service.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developers.dto.UserDTO;
import com.developers.exception.RestException;
import com.developers.model.User;
import com.developers.repository.UserRepository;
import com.developers.service.iface.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired private UserRepository userRepo;
	
	@Override
	public List<UserDTO> listUsers() throws RestException {
		List<User> usersDB = (List<User>) userRepo.findAll();
		List<UserDTO> listUsers = new ArrayList<>();
		usersDB.stream().forEach(u -> {
			UserDTO users = new UserDTO();
			users.setId(u.getIdUser());
			users.setNombre(u.getFirstName());
			users.setApellido(u.getLastName());
			users.setUsername(u.getUserName());
			users.setFechaNacimiento(u.getDateBirth());
			users.setEnabled(u.getEnabledUser());
			users.setImage(u.getImageUser());
			List<String> rols = u.getRoles().stream()
											.map(r -> r.getNameRole())
											.collect(Collectors.toList());
			users.setRoles(rols);
			listUsers.add(users);
		});
		return listUsers;
	}

	@Override
	public User listUser(Long id) throws RestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(User usuario) throws RestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User listByUsername() throws RestException {
		// TODO Auto-generated method stub
		return null;
	}

}
