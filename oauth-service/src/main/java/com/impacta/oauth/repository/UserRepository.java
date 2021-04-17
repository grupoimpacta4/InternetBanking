package com.impacta.oauth.repository;
 
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.impacta.oauth.model.UserDao;

@Repository
public interface UserRepository extends  JpaRepository<UserDao, Integer> {

	UserDao findByUsername(String user_login_cpf);
 
}