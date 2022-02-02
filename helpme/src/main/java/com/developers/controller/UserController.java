package com.developers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	
	@ApiOperation(value = "Obtiene una usuario por id", response = UserDTO.class, responseContainer = "List",
			produces = "application/json", httpMethod = "GET")
	@PostMapping("/show/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<User> create(@RequestBody User usuario) throws RestException{
		emailService.sendEmail(null, null, null);
		return null;		
	}
}
