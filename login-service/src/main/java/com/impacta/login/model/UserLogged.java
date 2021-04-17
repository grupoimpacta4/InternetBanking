package com.impacta.login.model;

import java.util.Date;

/**
 * @author User
 *
 */
public class UserLogged {
    private String username; 
    private String  email;  
    private String  nome;  
	private Date createdDate;
	private String token;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	private String conta;
	private String agencia;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	 

    
   

	public String getEmail() {
		return email;
	}

	public void setEmail(String  email) {
		this.email =  email;
	}

	 
   

	 
}
