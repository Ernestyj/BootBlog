package com.eugene;

import com.eugene.controller.MainController;
import com.google.common.collect.Sets;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket adminApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("admins")
				.apiInfo(apiInfo())
				.forCodeGeneration(true)
				.select()
				.paths(regex("/admins.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Springfox REST API")
				.description("Descriptions.")
				.termsOfServiceUrl("http://springfox.io")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
				.version("2.0")
				.build();
	}

	@Bean
	public Docket configSpringfoxDocket_all() {
		return new Docket(DocumentationType.SWAGGER_2)
				.produces(Sets.newHashSet("application/json"))
				.consumes(Sets.newHashSet("application/json"))
				.protocols(Sets.newHashSet("http"))
//                .protocols(Sets.newHashSet("http", "https"))
				.forCodeGeneration(true)
				.select().paths(regex(".*"))
				.build();
	}

}
