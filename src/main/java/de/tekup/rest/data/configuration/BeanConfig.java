package de.tekup.rest.data.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	// factory Method to create Bean
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
