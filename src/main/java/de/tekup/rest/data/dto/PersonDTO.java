package de.tekup.rest.data.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	
	private long id;
	private String name;
	private LocalDate dateOfBirth;

}
