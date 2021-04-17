package br.com.microservice.customer.gateway.service;

import br.com.microservice.customer.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BankAccountService {

    private SavingsAccountService savingsAccountService;
    private CurrentAccountService currentAccountService;

    @Autowired
    public BankAccountService(SavingsAccountService savingsAccountService, CurrentAccountService currentAccountService) {
        this.savingsAccountService = savingsAccountService;
        this.currentAccountService = currentAccountService;
    }

    public Customer generateBankAccount(Customer customer) {
        return checkTypeAccount(customer);
    }

    private Customer checkTypeAccount(Customer customer){
            customer = currentAccountService.generateCurrentAccount(customer);
            customer = savingsAccountService.generateSavingsAccount(customer);
            return customer;
    }
}
