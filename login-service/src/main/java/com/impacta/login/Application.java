package com.impacta.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.impacta.login.model.LoginDao;
import com.impacta.login.repository.UserRepository;
import com.impacta.login.service.JwtUserDetailsService;
 
 
@EnableAutoConfiguration
@ComponentScan({"com.*","com.impacta","com.impacta.login","com.impacta.login.config","com.impacta.login.controller","com.impacta.login.service.*","com.impacta.login.repository","com.impacta.login.controller","com.impacta.login.repository","com.impacta.login.model"})
@EntityScan(basePackageClasses =  LoginDao.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication(scanBasePackages ={"com.impacta","com.impacta.login","com.impacta.login.controller","com.impacta.login.service","com.impacta.login.repository","com.impacta.login.config","com.impacta.login.controller","com.impacta.login.repository","com.impacta.login.model"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public JwtUserDetailsService service() {
        return new JwtUserDetailsService();
    }
   
   
    
    
    
    
    
}
