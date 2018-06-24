package com.lesuorac.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class NpeConfig {
	final Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(Predicates.and(input -> {
			log.trace("name: {}, class: {}, 's package: {}", input.getName(), input.declaringClass(),
					input.declaringClass().getPackage());
			return true;
		}, RequestHandlerSelectors.basePackage("com.lesuorac"), RequestHandlerSelectors.any()))
				.paths(PathSelectors.any()).build();
	}

}
