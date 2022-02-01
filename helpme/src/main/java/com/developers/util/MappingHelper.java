package com.developers.util;

import java.util.List;
import java.util.stream.Collectors;

import com.developers.dto.UserDTO;
import com.developers.model.User;

public interface MappingHelper {

	public static UserDTO getMapValuesClient(User user) {
		UserDTO uDTO = new UserDTO();
		uDTO.setId(user.getIdUser());
		uDTO.setNombre(user.getFirstName());
		uDTO.setApellido(user.getLastName());
		uDTO.setFechaNacimiento(user.getDateBirth());
		uDTO.setImage(user.getImageUser());
		uDTO.setEnabled(user.getEnabledUser());
		List<String> rolsDTO = user.getRoles().stream().map(r -> r.getNameRole()).collect(Collectors.toList());
		uDTO.setRoles(rolsDTO);		
		uDTO.setUsername(user.getUserName());		
		return uDTO;
	}
	
	public static void setMapValuesCLient(List<User> usuarios, List<UserDTO> usuariosDto) {
		usuarios.stream()
				.map(usuario -> {
					UserDTO uDto = getMapValuesClient(usuario);
					return uDto;
				}).forEach(cDto -> {
					usuariosDto.add(cDto);
				});
	}
}
