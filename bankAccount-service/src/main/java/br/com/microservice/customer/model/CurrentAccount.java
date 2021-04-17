package br.com.microservice.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "current_account")
public class CurrentAccount{

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
    private static Long NEXTACCOUNTNUMBER = 11223145L;
    @Column(name="limit_Current", nullable = false)
    private BigDecimal limitCurrent = new BigDecimal(0);

    @OneToOne(cascade=CascadeType.PERSIST)
    private CreditCard creditCard;
 
 
}
