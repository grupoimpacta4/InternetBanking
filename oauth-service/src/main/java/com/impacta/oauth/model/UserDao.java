package com.impacta.oauth.model;
 

import javax.persistence.*; 
import javax.validation.constraints.NotEmpty;
 

@Entity(name = "users")
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(unique=true, nullable = false) 
    private String username;

	@Column( nullable = false) 
    private String password;
	
    @Column(nullable = false) 
    private int enabled;
   
    public String getPassword() {
        return password;
    }
    public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	public void setEnable(int i) {
		// TODO Auto-generated method stub
		
	}


}

