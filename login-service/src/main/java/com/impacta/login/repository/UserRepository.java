package com.impacta.login.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.impacta.login.model.LoginDao;

@Component
@Repository
public interface UserRepository extends CrudRepository<LoginDao, Integer> {

	LoginDao findByUsername(String user_login_cpf);
 
}