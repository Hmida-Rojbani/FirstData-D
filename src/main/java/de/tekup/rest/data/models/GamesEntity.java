package de.tekup.rest.data.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@EqualsAndHashCode(of = {"title","type"})
public class GamesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String type;
	
	@ManyToMany
	@JoinTable(name = "PlayedBy")
	@JsonIgnore
	private List<PersonEntity> persons;





	
}
