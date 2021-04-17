package com.impacta.login.service;

 import com.impacta.login.model.LoginDao;
import com.impacta.login.model.LoginDto;
import com.impacta.login.repository.UserRepository;

import org.apache.log4j.Logger; 
import javax.persistence.Query; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date; 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired 
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Resource
	private MessageSource messageSource;

	private static final Logger LOGGER = Logger.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String user_login_cpf) {
		LoginDao user = userDao.findByUsername(user_login_cpf);
		if (user == null || !(user.isActive() == '1')) {
			throw new UsernameNotFoundException("User not found with username: " + user_login_cpf);
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public LoginDao getUserByUsername(String user_login_cpf) {
		LoginDao user = userDao.findByUsername(user_login_cpf);
		if (user == null || !(user.isActive() == '1')) {
			throw new UsernameNotFoundException("User not found with username: " + user_login_cpf);
		}
		return user;
	}	
	
	public boolean userNameAlreadExist(String user_login_cpf) {
		LOGGER.info("Searching login by username");

		LoginDao user = userDao.findByUsername(user_login_cpf);
		if (user == null || !(user.isActive() == '1')) {
			LOGGER.info("User was not found");
			return false;
		}
		LOGGER.info("User was  found");

		return true;
	}

	public List<LoginDao> searchingByEmail(LoginDto user) {
		LOGGER.info("Searching login by email");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("user_login");
		EntityManager manager = factory.createEntityManager();
		LOGGER.info("Connecting at database");
		Query query = manager.createQuery("select email from login as ul " + "where ul.email = :email");
		query.setParameter("email", user.getEmail());
		List<LoginDao> lista = query.getResultList();
		LOGGER.info("Query login by email was sent");
		return lista;
	}

	public LoginDao save(LoginDto user) throws Exception {
		bcryptEncoder = new BCryptPasswordEncoder();
		String a = BCrypt.gensalt();
		String senhaCrip=BCrypt.hashpw(user.getPassword(), a);
		LOGGER.info("Entering at save login method");
		LoginDao newUser = new LoginDao();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(senhaCrip);
		newUser.setEmail(user.getEmail());
		newUser.setActive(user.isActive());
		newUser.setCreatedDate(new Date(System.currentTimeMillis()));
		return userDao.save(newUser);

	}

}