package br.com.microservice.customer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_card")
@Table(name = "creditcard")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCard {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardNumber;
    private String holderName;
    @Column(name="security_code", nullable = false,columnDefinition="int default 0")
    private int securityCode;
    private String validThru;
    private String password; 
    private BigDecimal limitCurrent = new BigDecimal(0);
}
