package com.impacta.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
import com.impacta.transaction.model.TransactionDao;
import com.impacta.transaction.repository.TransactionRepository;

@EnableAutoConfiguration
@ComponentScan({ "com.*", "com.impacta", "com.impacta.transaction", "com.impacta.transaction.config",
		"com.impacta.transaction.controller", "com.impacta.transaction.service.*", "com.impacta.transaction.repository",
		"com.impacta.transaction.controller", "com.impacta.transaction.repository", "com.impacta.transaction.model" })
@EntityScan(basePackageClasses = TransactionDao.class)
@EnableJpaRepositories(basePackageClasses = TransactionRepository.class)
@SpringBootApplication(scanBasePackages = { "com.impacta", "com.impacta.transaction", "com.impacta.login.controller",
		"com.impacta.transaction.service", "com.impacta.transaction.repository", "com.impacta.transaction.config",
		"com.impacta.transaction.controller", "com.impacta.transaction.repository", "com.impacta.transaction.model" })
	public class Application {
	
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	 
	}
