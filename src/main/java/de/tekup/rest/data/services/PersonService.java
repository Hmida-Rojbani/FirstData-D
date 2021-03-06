package de.tekup.rest.data.services;

import java.util.List;

import de.tekup.rest.data.dto.GameType;
import de.tekup.rest.data.dto.PersonDTO;
import de.tekup.rest.data.dto.PersonRequest;
import de.tekup.rest.data.models.PersonEntity;

public interface PersonService {
	
	List<PersonEntity> getAllEntities();
	PersonEntity getEntityById(long id);
	PersonDTO createEntity(PersonRequest entity);
	PersonEntity modifyEntity(long id, PersonEntity newEntity);
	PersonEntity deleteEntity(long id);
	public List<PersonEntity> getAllByOperator(String operator);
	public double getAverageAge();
	public List<PersonEntity> getPersonsPlayMostType();
	public List<GameType> getTypesAndNumber();
	public PersonEntity getPersonByName(String name);
	

}
