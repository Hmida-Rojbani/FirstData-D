package de.tekup.rest.data.services;

import java.util.List;

import de.tekup.rest.data.models.PersonEntity;

public interface PersonService {
	
	List<PersonEntity> getAllEntities();
	PersonEntity getEntityById(long id);
	PersonEntity createEntity(PersonEntity entity);
	PersonEntity modifyEntity(long id, PersonEntity newEntity);
	PersonEntity deleteEntity(long id);
	public List<PersonEntity> getAllByOperator(String operator);
	public double getAverageAge();
	public List<PersonEntity> getPersonsPlayMostType();
	

}
