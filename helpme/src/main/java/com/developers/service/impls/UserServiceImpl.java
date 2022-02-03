package com.developers.service.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.developers.dto.UserDTO;
import com.developers.exception.BadRequestException;
import com.developers.exception.ErrorDto;
import com.developers.exception.NotFoundException;
import com.developers.exception.RestException;
import com.developers.model.Role;
import com.developers.model.User;
import com.developers.repository.UserRepository;
import com.developers.service.iface.IUserService;
import com.developers.util.ConstantesUtil;

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
		Optional<User> userBD = Optional.ofNullable(userRepo.findById(id).get());
		if(!userBD.isPresent()) {
			throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
															 ConstantesUtil.MESSAGE_NOT_FOUND, 
															 HttpStatus.NOT_FOUND.value()));
		}
		return userRepo.findById(id).get();
	}

	@Override
	public User saveUser(User usuario) throws RestException {
		if(Objects.isNull(usuario)) {
			throw new BadRequestException(ErrorDto.getErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), 
																		 ConstantesUtil.MESSAGE_BAD_REQUEST, 
																		 HttpStatus.BAD_REQUEST.value()));
		}
		User usuarioDb = userRepo.findByUserName(usuario.getUserName());
		if(Objects.nonNull(usuarioDb)) {
			throw new BadRequestException(ErrorDto.getErrorDto(
					HttpStatus.BAD_REQUEST.getReasonPhrase(), 
					"Usuario ya existe",
					HttpStatus.BAD_REQUEST.value())
					);
		}
		List<Role> roles = new ArrayList<>();
		Role rol = new Role();
		rol.setIdRole(2L);
		roles.add(rol);
		usuario.setRoles(roles);
		return userRepo.save(usuario);
	}

	@Override
	public User listByUsername() throws RestException {
		// TODO Auto-generated method stub
		return null;
	}

}
