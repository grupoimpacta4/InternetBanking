package com.impacta.transaction.model;

public class TransferDTO {

	private int type_account_from;

	private int type_account_to;

	private String document_to;

	private double value;

	private String description_extract;

	public int getType_account_from() {
		return type_account_from;
	}

	public void setType_account_from(int type_account_from) {
		this.type_account_from = type_account_from;
	}

	public int getType_account_to() {
		return type_account_to;
	}

	public void setType_account_to(int type_account_to) {
		this.type_account_to = type_account_to;
	}

	public String getDocument_to() {
		return document_to;
	}

	public void setDocument_to(String document_to) {
		this.document_to = document_to;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDescription_extract() {
		return description_extract;
	}

	public void setDescription_extract(String description_extract) {
		this.description_extract = description_extract;
	}

}
