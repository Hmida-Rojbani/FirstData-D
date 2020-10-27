package de.tekup.rest.data.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.rest.data.models.AddressEntity;
import de.tekup.rest.data.models.PersonEntity;
import de.tekup.rest.data.models.TelephoneNumberEntity;
import de.tekup.rest.data.repositories.AddressRepository;
import de.tekup.rest.data.repositories.PersonRepository;
import de.tekup.rest.data.repositories.TelephoneNumberRepository;

@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonRepository reposPerson;
	private AddressRepository reposAddress;
	private TelephoneNumberRepository reposTelephone;
	
	@Autowired
	public PersonServiceImpl(PersonRepository reposPerson, AddressRepository reposAddress,
			TelephoneNumberRepository reposTelephone) {
		super();
		this.reposPerson = reposPerson;
		this.reposAddress = reposAddress;
		this.reposTelephone = reposTelephone;
	}


	@Override
	public List<PersonEntity> getAllEntities() {
		return reposPerson.findAll();
	}


	public PersonEntity getEntityById(long id) {
		Optional<PersonEntity> opt = reposPerson.findById(id);
		PersonEntity entity;
		if(opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("Person with this id is not found");
		
		return entity;
	}

	@Override
	public PersonEntity createEntity(PersonEntity entity) {
		// ajouter save de l'addresse
		AddressEntity addressEntity = entity.getAddress();
		
		AddressEntity addressEntityInBase = reposAddress.save(addressEntity);
		
		// update en niveau Person
		addressEntityInBase.setPerson(entity);
		 
		PersonEntity personInBase = reposPerson.save(entity);
		
		for (TelephoneNumberEntity phone : entity.getPhones()) {
			phone.setPerson(personInBase);
			reposTelephone.save(phone);
		}
		
		
		return personInBase;
	}

	@Override
	public PersonEntity modifyEntity(long id, PersonEntity newEntity) {
		PersonEntity entity = this.getEntityById(id);
		
		if(newEntity.getName() != null)
			entity.setName(newEntity.getName());
		if(newEntity.getDateOfBirth() != null)
			entity.setDateOfBirth(newEntity.getDateOfBirth());
		if(newEntity.getAddress() != null)
			entity.setAddress(newEntity.getAddress());
		
		return reposPerson.save(entity);
	}

	@Override
	public PersonEntity deleteEntity(long id) {
		PersonEntity entity = this.getEntityById(id);
		reposPerson.deleteById(id);
		return entity;
	}

}
