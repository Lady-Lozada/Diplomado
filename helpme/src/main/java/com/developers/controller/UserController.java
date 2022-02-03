package com.developers.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developers.dto.CrimeDTO;
import com.developers.dto.UserDTO;
import com.developers.exception.RestException;
import com.developers.model.User;
import com.developers.service.iface.IEmailService;
import com.developers.service.iface.IUserService;
import com.developers.util.MappingHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/users")
@Api(value = "users", tags = {"Users"})
@SwaggerDefinition(tags = {
		@Tag(name = "Users", description = "Gestión API Usuarios")
})
public class UserController {
	
	@Autowired IUserService userService;
	@Autowired IEmailService emailService;

	@ApiOperation(value = "Obtiene una lista de todos los usuarios", response = UserDTO.class, responseContainer = "List",
			produces = "application/json", httpMethod = "GET")
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<UserDTO>> index() throws RestException{
		List<UserDTO> usuarios = userService.listUsers();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@ApiOperation(value = "Obtiene una usuario por id", response = UserDTO.class, responseContainer = "List",
			produces = "application/json", httpMethod = "GET")
	@GetMapping("/show/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<UserDTO> show(@PathVariable Long id) throws RestException{
		User usuario = userService.listUser(id);
		UserDTO usuarios = MappingHelper.getMapValuesClient(usuario);
		return ResponseEntity.ok().body(usuarios);
	}
	
	@ApiOperation(value = "Da de alta a un usuario en la app", response = UserDTO.class, responseContainer = "List",
			produces = "application/json", httpMethod = "POST")
	@PostMapping("/singup")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<UserDTO> create(@RequestBody User usuario) throws RestException{
		User userSaved = userService.saveUser(usuario);
		if(Objects.nonNull(userSaved)) {
			String msg = "Su usuario: "+ userSaved.getUserName() + " y contraseña " + userSaved.getPassword();
			String to = userSaved.getUserName();
			String subj = "Registro en HelpMe IUD";
			boolean sent = emailService.sendEmail(msg, to, subj);
			if(Boolean.FALSE.equals(sent)) {
				// TODO: Pendiente implementar un log y una excepcion
			}
		}

		UserDTO usuarios = MappingHelper.getMapValuesClient(userSaved);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarios);		
	}
}
