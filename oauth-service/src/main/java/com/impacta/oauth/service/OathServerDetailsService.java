package com.impacta.oauth.service;
  
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.oauth.model.UserDao;
import com.impacta.oauth.model.UserDto;
import com.impacta.oauth.repository.UserRepository;

@Service
public class OathServerDetailsService {

	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private OathServerDetailsService userDetailsService;

	@Resource
	private MessageSource messageSource;

	public OathServerDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	@Modifying
	public int saveUser(UserDto user) {
		String a = BCrypt.gensalt();
	System.out.println("salt" + a);
	String senhahass=BCrypt.hashpw(user.getPassword(), a);
 
	System.out.println("enc" + senhahass);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("oauth_login");

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("INSERT INTO users (username, password, enabled) VALUES  (?, ?, ?)");
		query.setParameter(1, user.getUsername());
		query.setParameter(2, senhahass);
		query.setParameter(3, user.getEnabled());
		System.out.println("Passei aqui 1");
		int teste = query.executeUpdate();
		manager.getTransaction().commit();
		System.out.println("Passei aqui 2");
		return teste;
	}
 
	
	
	
	
	
	@Transactional
	@Modifying
	public int saveAuthority(String user, String autorithy) throws Exception {
 
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("oauth_login");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query = manager.createNativeQuery("INSERT INTO authorities (username, authority) VALUES  (?, ?)");
		query.setParameter(1, user);
		query.setParameter(2, autorithy);
		System.out.println("Passei aqui 1");
		int teste = query.executeUpdate();
		manager.getTransaction().commit();
		System.out.println("Passei aqui 2");
		return teste;
	}
	
	
	public List<UserDao> searchingByUserName(UserDto user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("oauth_login");
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("select username from users as ul " + "where ul.username = :username");
		query.setParameter("username", user.getUsername());
		List<UserDao> lista = query.getResultList();
		return lista;
	}
	
}
