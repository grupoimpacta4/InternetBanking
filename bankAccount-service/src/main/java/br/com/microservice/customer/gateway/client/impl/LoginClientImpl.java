package br.com.microservice.customer.gateway.client.impl;

import br.com.microservice.customer.exception.CustomerBusinessException;
import br.com.microservice.customer.gateway.client.LoginClient;
import br.com.microservice.customer.gateway.http.request.RequestLogin;
import br.com.microservice.customer.gateway.http.response.ResponseLogin;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginClientImpl implements LoginClient {

    private LoginClient loginClient;
    private RetryTemplate retryTemplate;

    @Autowired
    public LoginClientImpl(LoginClient loginClient, RetryTemplate retryTemplate) {
        this.loginClient = loginClient;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public ResponseLogin addLogin(RequestLogin requestLogin) {
        try {
            return retryTemplate.execute(c -> {
                log.info("[LoginClientImpl] - Request to Api Login");
                return loginClient.addLogin(requestLogin); });
        } catch (Exception e) {
            throw new CustomerBusinessException("Fail request to Api Login", HttpStatus.SC_BAD_REQUEST);
        }
    }
}