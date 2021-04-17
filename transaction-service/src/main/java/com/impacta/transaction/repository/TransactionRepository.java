package com.impacta.transaction.repository;
 import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.transaction.model.TransactionDao;

@Transactional
@Repository
public interface TransactionRepository extends CrudRepository<TransactionDao, Long> {

 
	 
}