package br.com.microservice.customer.model;

import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Data;

@Data
@DiscriminatorValue("Visa")
@Entity
public class Visa extends CreditCard{

    private String cardName;

    @Transient
    private static Random random = new Random();

    public String generateNumberCard(){
        return "4539.****.****."+Integer.toString(random.nextInt(9999) + 1000);
    }

    public String generateValidCard(){
        return Integer.toString(random.nextInt(12) + 01)+ "/202" +Integer.toString(random.nextInt(9) + 2);
    }

    public String generatePassword(){
        return Integer.toString(random.nextInt(9999) + 1000);
    }

    public int generateSecurityCode(){
        return random.nextInt(999) + 100;
    }
}


