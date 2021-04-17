package com.impacta.transaction.model;

import java.util.ArrayList;
import java.util.List;

public class Extrato {
	
	private String tipoConta;
    private String Moeda;
    private String Simbolo;
    private List<TransactionDao> transactions = new ArrayList<TransactionDao>();
    
    public Extrato(String tipoConta, String moeda, String simbolo, List<TransactionDao> transactions) {
		super();
		this.tipoConta = tipoConta;
		Moeda = moeda;
		Simbolo = simbolo;
		this.transactions = transactions;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public String getMoeda() {
		return Moeda;
	}
	public void setMoeda(String moeda) {
		Moeda = moeda;
	}
	public String getSimbolo() {
		return Simbolo;
	}
	public void setSimbolo(String simbolo) {
		Simbolo = simbolo;
	}
 
	public List<TransactionDao> getListTransactions() {
		return transactions;
	}
	public void setListTransactions(List<TransactionDao> listTransactions) {
		this.transactions = listTransactions;
	}
	 
	 
	 	 
}
