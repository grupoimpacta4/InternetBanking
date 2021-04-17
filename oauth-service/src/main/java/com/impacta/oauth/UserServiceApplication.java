package com.impacta.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.impacta.oauth.model.UserDao;
import com.impacta.oauth.repository.UserRepository;
 

@EnableAutoConfiguration
@ComponentScan({"com.*","com.impacta","com.impacta.oauth","com.impacta.oauth.config","com.impacta.oauth.controller","com.impacta.oauth.service.*","com.impacta.oauth.repository","com.impacta.oauth.controller","com.impacta.oauth.repository","com.impacta.oauth.model","com.impacta.oauth.config.security"})
@EntityScan(basePackageClasses =  UserDao.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication(scanBasePackages ={"com.impacta","com.impacta.oauth","com.impacta.oauth.controller","com.impacta.oauth.service","com.impacta.oauth.repository","com.impacta.oauth.config","com.impacta.oauth.controller","com.impacta.oauth.repository","com.impacta.oauth.model","com.impacta.oauth.config.security"})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

  

}
