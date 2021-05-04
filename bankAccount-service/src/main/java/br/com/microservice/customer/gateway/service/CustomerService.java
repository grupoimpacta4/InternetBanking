package br.com.microservice.customer.gateway.service;

import br.com.microservice.customer.exception.CustomerBusinessException;
import br.com.microservice.customer.gateway.client.impl.LoginClientImpl;
import br.com.microservice.customer.gateway.http.request.RequestLogin;
import br.com.microservice.customer.gateway.repository.CustomerRepository;
import br.com.microservice.customer.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
 
 

@Slf4j
@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private ModelMapper modelMapper;
	private BankAccountService bankAccountService;
	private LoginClientImpl loginClientImpl;
	private static final Logger LOGGER = Logger.getLogger(CustomerService.class);

	@Autowired
	public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper,
			BankAccountService bankAccountService, LoginClientImpl loginClientImpl) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
		this.bankAccountService = bankAccountService;
		this.loginClientImpl = loginClientImpl;
	}

	public  ResponseDto findByDocument(String document) {
		Customer newCustomer = customerRepository.findAllByDocument(document)
				.orElseThrow(() -> new CustomerBusinessException("Customer not found", HttpStatus.NOT_FOUND.value()));
		return modelMapper.map(newCustomer, ResponseDto.class);

	}

	 
	
	public boolean userNameAlreadExist(CustomerDto user) {
		LOGGER.info("Searching login by username");

		Customer newUser =  findByUsername( user);
		if (newUser == null ) {
			LOGGER.info("User was not found");
			return false;
		}
		LOGGER.info("User was  found");
		return true;
	}

	
	public Customer  findByUsername(CustomerDto customer) {
		LOGGER.info("Searching login by cpf");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("customer");
		EntityManager manager = factory.createEntityManager();
		LOGGER.info("Connecting at database");
		Query query = manager.createQuery("from customer as c " + "where c.document = :document");
		query.setParameter("document", customer.getDocument());
		List<Customer> lista  =   query.getResultList();
		LOGGER.info("Query login by cpf was sent");
		if(lista.size()>0)
			return lista.get(0);
		return null;
	}
	
	public List<Customer> searchingByEmail(CustomerDto user) {
		LOGGER.info("Searching login by email");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("customer");
		EntityManager manager = factory.createEntityManager();
		LOGGER.info("Connecting at database");
		Query query = manager.createQuery("select email from customer as ul " + "where ul.email = :email");
		query.setParameter("email", user.getEmail());
		List<Customer> lista = query.getResultList();
		LOGGER.info("Query login by email was sent");
		return lista;
	}
	
	
	
	public ResponseDto save(CustomerDto customerDto) {
		Customer newCustomer = new Customer();
		RequestLogin requestLogin = convertCustomerToLoginRequest(customerDto);
		newCustomer = modelMapper.map(customerDto, Customer.class);
		newCustomer = bankAccountService.generateBankAccount(newCustomer);

		try {
			loginClientImpl.addLogin(requestLogin);
			System.out.println("Customer save document number: " + "***" + customerDto.getDocument().substring(3, 6) + "***");
			System.out.println("digito corrente = "+ newCustomer.getCurrentAccount().getCheckDigit());
			System.out.println("digito pop  = "+ newCustomer.getSavingsAccount().getCheckDigit());
			System.out.println("cont corrente  = "+ newCustomer.getCurrentAccount().getAccountNumber());
			System.out.println("cont pop  = "+ newCustomer.getSavingsAccount().getAccountNumber());
			
			log.info("Customer save document number: " + "***" + customerDto.getDocument().substring(3, 6) + "***");
			return modelMapper.map(customerRepository.save(newCustomer), ResponseDto.class);
		} catch (CustomerBusinessException e) {
			log.error("Fail request to Api Login:");
			throw new CustomerBusinessException("Fail request to Api Login", HttpStatus.BAD_REQUEST.value());
		} catch (DataAccessException e) {
			log.error("Customer already registered:");
			throw new CustomerBusinessException("Customer already registered", HttpStatus.BAD_REQUEST.value());
		}
	}

	private RequestLogin convertCustomerToLoginRequest(CustomerDto customer) {
		RequestLogin requestLogin = new RequestLogin();
		requestLogin.setUsername(customer.getDocument());
		requestLogin.setPassword(customer.getPassword());
		requestLogin.setEmail(customer.getEmail());
		requestLogin.setActive("1");
		return requestLogin;
	}

}