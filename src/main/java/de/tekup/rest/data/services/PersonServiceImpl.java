package de.tekup.rest.data.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.rest.data.models.AddressEntity;
import de.tekup.rest.data.models.GamesEntity;
import de.tekup.rest.data.models.PersonEntity;
import de.tekup.rest.data.models.TelephoneNumberEntity;
import de.tekup.rest.data.repositories.AddressRepository;
import de.tekup.rest.data.repositories.GamesRepository;
import de.tekup.rest.data.repositories.PersonRepository;
import de.tekup.rest.data.repositories.TelephoneNumberRepository;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository reposPerson;
	private AddressRepository reposAddress;
	private TelephoneNumberRepository reposTelephone;
	private GamesRepository reposGames;

	@Autowired
	public PersonServiceImpl(PersonRepository reposPerson, AddressRepository reposAddress,
			TelephoneNumberRepository reposTelephone, GamesRepository reposGames) {
		super();
		this.reposPerson = reposPerson;
		this.reposAddress = reposAddress;
		this.reposTelephone = reposTelephone;
		this.reposGames = reposGames;
	}

	@Override
	public List<PersonEntity> getAllEntities() {
		return reposPerson.findAll();
	}

	public PersonEntity getEntityById(long id) {
		Optional<PersonEntity> opt = reposPerson.findById(id);
		PersonEntity entity;
		if (opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("Person with this id is not found");

		return entity;
	}

	// take on consideration the GamesEntity
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

		List<GamesEntity> games = entity.getGames();
		List<GamesEntity> gamesInBase = reposGames.findAll();

		games.forEach(game -> {
			List<GamesEntity> gamesInBaseTmp = new ArrayList<>(gamesInBase);
			gamesInBaseTmp.removeIf(gTmp -> !gTmp.equals(game));
			if (gamesInBaseTmp.isEmpty()) {
				List<PersonEntity> persons = new ArrayList<PersonEntity>();
				persons.add(personInBase);
				game.setPersons(persons);
				reposGames.save(game);
			} else {
				gamesInBaseTmp.get(0).getPersons().add(personInBase);
				reposGames.save(gamesInBaseTmp.get(0));

			}
		});

		/*
		 * for (GamesEntity game : games) { // verify the existance of game on table
		 * if(!gamesInBase.contains(game)) { List<PersonEntity> persons = new
		 * ArrayList<PersonEntity>(); persons.add(personInBase);
		 * game.setPersons(persons); reposGames.save(game); }else { for (GamesEntity
		 * gameInBase : gamesInBase) { if(gameInBase.equals(game)) {
		 * gameInBase.getPersons().add(personInBase); reposGames.save(gameInBase);
		 * break; } }
		 * 
		 * } }
		 */

		return personInBase;
	}

	@Override
	// update for the new parts
	public PersonEntity modifyEntity(long id, PersonEntity newPerson) {
		// is there a better way to do this ? (3 point bonus dans DS)
		PersonEntity oldPerson = this.getEntityById(id);

		// modification de Person
		if (newPerson.getName() != null)
			oldPerson.setName(newPerson.getName());
		if (newPerson.getDateOfBirth() != null)
			oldPerson.setDateOfBirth(newPerson.getDateOfBirth());
		// modification Address
		AddressEntity newAddress = newPerson.getAddress();
		AddressEntity oldAddress = oldPerson.getAddress();
		if (newAddress != null) {
			if (newAddress.getNumber() != 0)
				oldAddress.setNumber(newAddress.getNumber());
			if (newAddress.getStreet() != null)
				oldAddress.setStreet(newAddress.getStreet());
			if (newAddress.getCity() != null)
				oldAddress.setCity(newAddress.getCity());
		}

		// modification phones
		List<TelephoneNumberEntity> oldPhones = oldPerson.getPhones();
		List<TelephoneNumberEntity> newPhones = newPerson.getPhones();

		if (newPhones != null) {
			for (int i = 0; i < newPhones.size(); i++) {
				TelephoneNumberEntity newPhone = newPhones.get(i);
				for (int j = 0; j < oldPhones.size(); j++) {
					TelephoneNumberEntity oldPhone = oldPhones.get(i);
					if (oldPhone.getId() == newPhone.getId()) {
						if (newPhone.getNumber() != null)
							oldPhone.setNumber(newPhone.getNumber());
						if (newPhone.getOperator() != null)
							oldPhone.setOperator(newPhone.getOperator());
						// stop the loop
						break;
					}
				}
			}
		}

		// modification games

		List<GamesEntity> oldGames = oldPerson.getGames();
		List<GamesEntity> newGames = newPerson.getGames();

		if (newGames != null) {
			for (GamesEntity newGame : newGames) {
				for (GamesEntity oldGame : oldGames) {
					if (oldGame.getId() == newGame.getId()) {
						if (newGame.getTitle() != null)
							oldGame.setTitle(newGame.getTitle());
						if (newGame.getType() != null)
							oldGame.setType(newGame.getType());
					}
				}
			}
		}

		return reposPerson.save(oldPerson);
	}

	@Override
	public PersonEntity deleteEntity(long id) {
		PersonEntity entity = this.getEntityById(id);
		reposPerson.deleteById(id);
		return entity;
	}
	
	// All person with a given operator
	public List<PersonEntity> getAllByOperator(String operator){
		return null;
	}

}
