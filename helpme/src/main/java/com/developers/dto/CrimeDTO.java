package com.developers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
@NoArgsConstructor
public class CrimeDTO {

	private Long id;
	private String name;
	private String description;
}
