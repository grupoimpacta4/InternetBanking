package br.com.microservice.customer.gateway.http.request;

import lombok.Data;

@Data
public class RequestLogin {

    private String username;
    private String password;
    private String  email;
    private String  active;
}
