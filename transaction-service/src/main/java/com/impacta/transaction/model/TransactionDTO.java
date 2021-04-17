package com.impacta.transaction.model;

 
public class TransactionDTO {

	    private long id;
		
	    private long accountId;
		
	    private String description_transaction;
		
	    private double value;		

	    private int statusTransaction;
	    
		private String descriptionStatus;
		
		private String description_type_transaction;
		

		private int typeTransactionId;
		
		private String money_name;
		
		private String money_symbol;
		
		private String type_operation;

		private String description_extract;
		
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

		public long getAccountId() {
			return accountId;
		}

		public void setAccountId(long accountId) {
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
 

		public String getType_operation() {
			return type_operation;
		}

		public void setType_operation(String type_operation) {
			this.type_operation = type_operation;
		}

		public String getMoney_symbol() {
			return money_symbol;
		}

		public void setMoney_symbol(String money_symbol) {
			this.money_symbol = money_symbol;
		}

		public String getGuidId() {
			return guidId;
		}

		public void setGuidId(String guidId) {
			this.guidId = guidId;
		}

	    
	    
	   
	
	 
}
