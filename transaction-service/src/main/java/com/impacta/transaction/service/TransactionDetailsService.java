package com.impacta.transaction.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacta.transaction.model.TransactionDTO;
import com.impacta.transaction.model.TransactionDao;
import com.impacta.transaction.repository.TransactionRepository;
 

@Service 
public class TransactionDetailsService {
	 
	
	@Autowired
	private TransactionRepository movimentacaoRepository;
 
	 
	
	public TransactionDetailsService() {
	 	
   }
	public List<TransactionDao> searchByAccountId(long contaCorrenteId, int status) {
		System.out.println(contaCorrenteId);
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactions");

		EntityManager manager = factory.createEntityManager();

		Query query = manager.createQuery("from transactions as mo "
				+ "where mo.accountId = :accountId and mo.statusTransaction = :statusTransaction ");
		query.setParameter("accountId", contaCorrenteId);
		query.setParameter("statusTransaction", status);

		List<TransactionDao> lista = query.getResultList();
		return lista;
	}

	public double getBalance(long contaCorrenteId, int status) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactions");

		EntityManager manager = factory.createEntityManager();

		Query query = manager.createQuery("from transactions as mo "
				+ "where mo.accountId = :accountId and mo.statusTransaction = :statusTransaction ");
		query.setParameter("accountId", contaCorrenteId);
		query.setParameter("statusTransaction", status);

		List<TransactionDao> listTransactions = query.getResultList();
		double saldo = 0.0;
		for (TransactionDao movimentacoesDao : listTransactions) {
			saldo = saldo + movimentacoesDao.getValue();
		}
		System.out.print("\n saldo "+saldo+"\n \n");
		return saldo;
		
	}

	public TransactionDao save(TransactionDTO transactionDTO) throws Exception {
	 	TransactionDao newTransaction = new TransactionDao();
	 	newTransaction.setAccountId(transactionDTO.getAccountId());
		newTransaction.setValue(transactionDTO.getValue());
		newTransaction.setDescription_transaction(transactionDTO.getDescription_transaction());
		newTransaction.setDescriptionStatus(transactionDTO.getDescriptionStatus());
		newTransaction.setStatusTransaction(transactionDTO.getStatusTransaction());
		newTransaction.setType_operation(transactionDTO.getType_operation());
		newTransaction.setMoney_name(transactionDTO.getMoney_name());
		newTransaction.setTypeTransactionId(transactionDTO.getTypeTransactionId());
		newTransaction.setMoney_symbol(transactionDTO.getMoney_symbol());
		newTransaction.setDescription_type_transaction(transactionDTO.getDescription_type_transaction());
		newTransaction.setCreatedDate(new Date(System.currentTimeMillis()));
		return movimentacaoRepository.save(newTransaction);
	 }

	@Transactional
	@Modifying
	public int addTransaction(TransactionDTO transactionDTO) {
	  
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactions");

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Query query  = manager.createNativeQuery("INSERT INTO transactions (cc_id, value, description_transaction,description_status,status,type_operation,money_name,type_transaction_id,money_symbol,description_type_transaction,created_date,description_extract,guidId) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)");
		query.setParameter(1,transactionDTO.getAccountId());
		query.setParameter(2,transactionDTO.getValue());
		query.setParameter(3,transactionDTO.getDescription_transaction());
		query.setParameter(4,transactionDTO.getDescriptionStatus());
		query.setParameter(5,transactionDTO.getStatusTransaction());
		query.setParameter(6,transactionDTO.getType_operation());
		query.setParameter(7,transactionDTO.getMoney_name());
		query.setParameter(8,transactionDTO.getTypeTransactionId());
		query.setParameter(9,transactionDTO.getMoney_symbol());
		query.setParameter(10,transactionDTO.getDescription_type_transaction());
		query.setParameter (11,new Date(System.currentTimeMillis()));
		query.setParameter (12, transactionDTO.getDescription_extract());
		query.setParameter (13, transactionDTO.getGuidId());
		
		
		System.out.println("Passei aqui 1");
		int teste = query.executeUpdate();
		manager.getTransaction().commit();
		System.out.println("Passei aqui 2");
		return teste;
	}
	
	
	public int deleteTransacationByGuid(TransactionDTO transactionDTO) {
		  
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactions");

				EntityManager manager = factory.createEntityManager();
				manager.getTransaction().begin();
				Query query  = manager.createNativeQuery("delete from transactions where guidId=? and cc_id=?");
				System.out.println("Passei aqui Guidid ==== "+ transactionDTO.getGuidId());
				System.out.println("Passei aqui Accountid ===="+transactionDTO.getAccountId());
				
				query.setParameter (1,transactionDTO.getGuidId());
				query.setParameter (2, transactionDTO.getAccountId());
				System.out.println("Passei aqui 1");
				int teste = query.executeUpdate();
				manager.getTransaction().commit();
				System.out.println("Passei aqui 2");
				return teste;	
	}

}