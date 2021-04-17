package com.impacta.login.model;

import java.util.Date;

public class LoginDto {
    private String username;
    private String password;
    private String  email; 
    private char  active; 
	private Date createdDate;
	
	public char isActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

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

	 
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	 
}
