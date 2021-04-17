package com.impacta.transaction.model;
 

public class Balance {
	
 
    private double saldo;

	public Balance(double saldo, String tipoConta, String moeda, String simbolo) {
		super();
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		Moeda = moeda;
		Simbolo = simbolo;
	}
	private String tipoConta;
    private String Moeda;
    private String Simbolo;
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

    
    public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	 	 
}
