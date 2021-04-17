package br.com.microservice.customer.gateway.service;

import br.com.microservice.customer.model.Customer;
import br.com.microservice.customer.model.SavingsAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class SavingsAccountService {
	private static Long NEXTACCOUNTNUMBER = 11223145L;
	int digit= (int) ((Math.random() * (9 - 0) + 1) + 0);
    public Customer generateSavingsAccount(Customer customer) {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setCheckDigit(digit);
        System.out.println(savingsAccount.getCheckDigit());
        savingsAccount.setAccountNumber(++NEXTACCOUNTNUMBER);
        savingsAccount.setStatus(true); 
        savingsAccount.setYieldCurrent(BigDecimal.valueOf(0));
        customer.setSavingsAccount(savingsAccount);
        return customer;
    }

}
