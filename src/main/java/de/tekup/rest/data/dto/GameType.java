package de.tekup.rest.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// DTO : data Transfer Object 

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "type")
public class GameType {

	private String type;
	private int number;
	
	public void increment() {
		number++;
	}
}
