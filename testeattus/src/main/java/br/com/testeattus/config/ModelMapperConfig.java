package br.com.testeattus.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	// Bean utilizado para instanciação do modelMapper
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
