package com.impacta.transaction.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.*;

@Entity(name = "transactions")
@Table(name = "transactions")
public class TransactionDao {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
	
	@Column(name="cc_Id")
    @JsonIgnore 
    private Long accountId;
	
	@Column
    @JsonIgnore
    private String description_transaction;
	
	@Column
    @JsonIgnore
    private double value;
	

	@Column(name="created_Date")
    @JsonIgnore
	private Date createdDate;

	

	@Column(name="status")
    @JsonIgnore
    private int statusTransaction;
    
	@Column(name="description_status")
    @JsonIgnore
	private String descriptionStatus;
	
	@Column(name="description_type_transaction")
    @JsonIgnore
	private String description_type_transaction;
	

	@Column(name="type_transaction_id")
    @JsonIgnore
	private int typeTransactionId;
	
	@Column
    @JsonIgnore
	private String money_name;
	
	@Column
    @JsonIgnore
	private String money_symbol;
	
	@Column
    @JsonIgnore
	private String type_operation;

	@Column
    @JsonIgnore
	private String description_extract;
	
	public String getGuidId() {
		return guidId;
	}

	public void setGuidId(String guidId) {
		this.guidId = guidId;
	}

	@Column(name="guidId")
    @JsonIgnore
	private String guidId;
	
	public String getDescription_extract() {
		return description_extract;
	}

	public void setDescription_extract(String description_extract) {
		this.description_extract = description_extract;
	}

	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getDescription_transaction() {
		return description_transaction;
	}

	public void setDescription_transaction(String description_transaction) {
		this.description_transaction = description_transaction;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStatusTransaction() {
		return statusTransaction;
	}

	public void setStatusTransaction(int statusTransaction) {
		this.statusTransaction = statusTransaction;
	}

	public String getDescriptionStatus() {
		return descriptionStatus;
	}

	public void setDescriptionStatus(String descriptionStatus) {
		this.descriptionStatus = descriptionStatus;
	}

	public String getDescription_type_transaction() {
		return description_type_transaction;
	}

	public void setDescription_type_transaction(String description_type_transaction) {
		this.description_type_transaction = description_type_transaction;
	}

	public int getTypeTransactionId() {
		return typeTransactionId;
	}

	public void setTypeTransactionId(int typeTransactionId) {
		this.typeTransactionId = typeTransactionId;
	}

	public String getMoney_name() {
		return money_name;
	}

	public void setMoney_name(String money_name) {
		this.money_name = money_name;
	}

	public String getMoney_symbol() {
		return money_symbol;
	}

	public void setMoney_symbol(String money_symbol) {
		this.money_symbol = money_symbol;
	}

	public String getType_operation() {
		return type_operation;
	}

	public void setType_operation(String type_operation) {
		this.type_operation = type_operation;
	}
 
    }

