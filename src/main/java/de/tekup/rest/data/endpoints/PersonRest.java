package de.tekup.rest.data.endpoints;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rest.data.dto.GameType;
import de.tekup.rest.data.dto.PersonDTO;
import de.tekup.rest.data.models.PersonEntity;
import de.tekup.rest.data.services.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonRest {
	
	private PersonService service;

	@Autowired
	public PersonRest(PersonService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<PersonEntity> getAll(){
		return service.getAllEntities();
	}
	
	@GetMapping("/{id}")
	public PersonDTO getById(@PathVariable("id")long id) {
		PersonEntity person = service.getEntityById(id);
		return new PersonDTO(person.getId(), person.getName(), person.getDateOfBirth());
	}
	
	@GetMapping("operator/{operator}")
	public List<PersonEntity> getAllByOperator(@PathVariable("operator")String operator) {
		return service.getAllByOperator(operator);
	}
	
	@PostMapping
	public PersonEntity createPerson(@RequestBody PersonEntity person) {
		return service.createEntity(person);
	}
	
	@PutMapping("/{id}")
	public PersonEntity modifyPreson(@PathVariable("id")long id,
							@RequestBody PersonEntity newPerson) {
		return service.modifyEntity(id, newPerson);
	}
	
	@DeleteMapping("/{id}")
	public PersonEntity deletePerson(@PathVariable("id")long id) {
		return service.deleteEntity(id);
	}
	
	@GetMapping("/average/age")
	public double getPersonsAverageAge() {
		return service.getAverageAge();
	}
	
	@GetMapping("/type/most")
	public List<PersonEntity> getPersonsPlayType() {
		return service.getPersonsPlayMostType();
	}
	
	@GetMapping("/type/number")
	public List<GameType> getPersonsNumberType() {
		return service.getTypesAndNumber();
	}
	
	@GetMapping("/name/{name}")
	public PersonEntity getByName(@PathVariable("name")String name) {
		return service.getPersonByName(name);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	

}
