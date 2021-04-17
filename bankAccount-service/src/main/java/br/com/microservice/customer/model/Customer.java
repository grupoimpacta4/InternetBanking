package br.com.microservice.customer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity(name = "customer")
@Table(name = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Transient
	@NotEmpty(message = "password.not.blank")
    private String password;
	
    @Column(unique=true, nullable = false)
    @Email(message = "email.not.valid")
    @NotEmpty(message = "email.not.blank")
    private String  email;

    private Date createdDate;
    @Column(unique=true, nullable = false)
    private String document;

    private String name; 
    private Date birthday;
    @Column(name="mother_name")
    private String motherName;
    @Column(name="father_name")
    private String fatherName;
    @Column(name="maritalStatus", nullable = false,columnDefinition="int default 0")
    private int maritalStatus;
    @Column(name="pin_card")
    private String pinCard;
    @Column(name="marital_status_description")
    private String maritalStatusDescription;
    private String profession;
    private String gender;
    private String type;

    @Transient
    private TypeAccount typeAccount;

    @OneToOne(cascade=CascadeType.PERSIST)
    private CurrentAccount currentAccount;

    @OneToOne(cascade=CascadeType.PERSIST)
    private SavingsAccount savingsAccount;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Address address;

    @PrePersist
    protected void createdDate() {
        createdDate = new Date();
    }
}

