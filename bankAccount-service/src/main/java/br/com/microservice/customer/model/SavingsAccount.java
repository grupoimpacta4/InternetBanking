package br.com.microservice.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "savings_account")
public class SavingsAccount{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String branch = "0900";
    @Column(name="account_Number", nullable = false)
    private Long  accountNumber;

    @Column(name="check_Digit", nullable = false,columnDefinition="int default 0")
    private int  checkDigit;
    private Boolean status; 
    @Column(name="yield_Current")
    private BigDecimal yieldCurrent = new BigDecimal(0);
    private static Long NEXTACCOUNTNUMBER = 11223145L;

     
  


}
