package com.developers.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.developers.dto.UserDTO;
import com.developers.exception.ErrorDto;
import com.developers.exception.NotFoundException;
import com.developers.exception.RestException;
import com.developers.model.User;
import com.developers.service.iface.IEmailService;
import com.developers.service.iface.IUserService;
import com.developers.util.ConstantesUtil;
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
	
	@ApiOperation(value = "Actualiza la foto del usuario en la app", response = UserDTO.class, responseContainer = "List",
			produces = "application/json", httpMethod = "POST")
	@PostMapping("/upload/{email}")
	public ResponseEntity<?> upload(@RequestParam("image") MultipartFile image, @PathVariable String email) throws RestException{
		Map<String, Object> response = new HashMap<>();
		User usuario = userService.listByUsername(email);
		if(!image.isEmpty()) {
			String nameImage = UUID.randomUUID().toString()
					.concat("_")
					.concat(image.getOriginalFilename().replace(" ", ""));
			Path path = Paths.get("uploads").resolve(nameImage).toAbsolutePath();
			try {
				Files.copy(image.getInputStream(), path);
			} catch (IOException e) {
				response.put("Error IO: ", e.getMessage().concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String imageDB = usuario.getImageUser();
			if(Objects.nonNull(imageDB) && imageDB.length() > 0 && !imageDB.startsWith("http")) {
				Path pathBefore = Paths.get("uploads").resolve(imageDB).toAbsolutePath();
				File imageFileBefore = pathBefore.toFile();
				if(imageFileBefore.exists() && imageFileBefore.canRead()) {
					imageFileBefore.delete();
				}
			}
			usuario.setImageUser(nameImage);
			userService.saveUserImage(usuario);
			response.put("Usuario", usuario);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Actualiza un usuario en la app", response = User.class,
			produces = "application/json", httpMethod = "PUT")
	@PutMapping("/update/{userName}")
	public ResponseEntity<User> update(@PathVariable String userName, @RequestBody User usuario ) throws RestException {
		User usuarioDB = userService.listByUsername(userName);
		if(Objects.isNull(usuarioDB)) {
			throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
															 ConstantesUtil.MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}		
		usuarioDB.setFirstName(usuario.getFirstName());
		usuarioDB.setLastName(usuario.getLastName());
		usuarioDB.setDateBirth(usuario.getDateBirth());
		usuarioDB.setPassword(usuario.getPassword()); // TODO: implementar con Spring Security
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(usuarioDB));
	}
}
