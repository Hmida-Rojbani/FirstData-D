package de.tekup.rest.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.tekup.rest.data.models.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

	Optional<PersonEntity> findByName(String name);
	Optional<PersonEntity> findByNameIgnoreCase(String name);
	// equivalance 
	//@Query("select p from PersonEntity p where p.name = ?1")
	//Optional<PersonEntity> getAvecNom(String nom);
	//@Query(value ="select p from PersonEntity p where p.name = :name",
			//nativeQuery = false)
	// Use the JPQL language
	@Query("select p from PersonEntity p "
			+ "where lower(p.name) = lower(:name)")
	Optional<PersonEntity> getAvecNom(@Param("name") String nom);
	
	
	
}
