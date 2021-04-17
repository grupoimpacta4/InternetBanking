package com.impacta.login.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email; 
import javax.validation.constraints.NotEmpty;
 

@Entity(name = "login")
@Table(name = "user_login")
public class LoginDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "username.not.blank")
    @Column(unique=true, nullable = false) 
    private String username;

	@Column( nullable = false)
	@NotEmpty(message = "password.not.blank")
    private String password;
	
    @Column(unique=true, nullable = false) 
    @Email(message = "email.not.valid")
    @NotEmpty(message = "email.not.blank")
    private String  email;
    
    @Column() 
    private char active;
    
    @Column()  
    private Date createdDate;
    
    public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public char isActive() {
		return active;
	}

	public void setActive(char ativo) {
		this.active = ativo;
	}

	public String getEmail() {
		return email;
	}

	 
	public void setEmail(String email) {
		this.email = email;
	}

 
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}

