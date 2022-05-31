package com.catsjump.anakena.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.catsjump.anakena.domain.CardPayment;
import com.catsjump.anakena.domain.SlipPayment;
import com.fasterxml.jackson.databind.ObjectMapper;

//classe de configuracao eh utilizada para setar configuracoes no start da aplicacao

@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(CardPayment.class);
				objectMapper.registerSubtypes(SlipPayment.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}