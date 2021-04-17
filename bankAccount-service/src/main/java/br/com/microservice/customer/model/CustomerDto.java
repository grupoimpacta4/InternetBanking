package br.com.microservice.customer.model;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDto {
	private String password;
	private String email;
	private Date createdDate;
	private String document;
	private String name; 
	private String type;
	private Date birthday;
	private String motherName;
	private String fatherName;
	private int maritalStatus;
	private String pinCard;
	private String maritalStatusDescription;
	private String profession;
	private String gender;
	private String cityOrigin;
	private String country;
	private TypeAccount typeAccount;
	private Address address;

}
