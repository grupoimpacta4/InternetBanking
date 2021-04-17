package br.com.microservice.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private String name;
    private String document;
    private String email;
    private CurrentAccount currentAccount;
    private SavingsAccount savingsAccount;
    //private CreditCard creditCard;

    public void setDocument(String document) {
        this.document = "***"+document.substring(3,6)+"***";
    }

}



