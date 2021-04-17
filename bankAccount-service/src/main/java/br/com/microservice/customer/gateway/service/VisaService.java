package br.com.microservice.customer.gateway.service;

import br.com.microservice.customer.model.Customer;
import br.com.microservice.customer.model.Visa;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class VisaService {

    public Visa setVisa(Customer customer) {
        Visa visa = new Visa();
        visa.setCardNumber(visa.generateNumberCard());
        visa.setCardName("VISA"); 
        visa.setLimitCurrent(BigDecimal.valueOf(1000));
        visa.setPassword(visa.generatePassword());
        visa.setHolderName(customer.getName());
        visa.setValidThru(visa.generateValidCard());
        visa.setSecurityCode(visa.generateSecurityCode());
        return visa;
    }
}
