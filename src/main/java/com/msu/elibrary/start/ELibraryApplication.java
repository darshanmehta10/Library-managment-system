package com.msu.elibrary.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.msu.elibrary")
@EnableJpaRepositories("com.msu.elibrary.repositories")
@EntityScan("com.msu.elibrary.entities")
public class ELibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(ELibraryApplication.class, args);
	}

//	@Bean
//	public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//		templateEngine.addDialect(new SpringSecurityDialect());
//		return templateEngine;
//	}
}
