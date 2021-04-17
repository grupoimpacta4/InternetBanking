package br.com.microservice.customer.gateway.client;


import br.com.microservice.customer.gateway.http.request.RequestLogin;
import br.com.microservice.customer.gateway.http.response.ResponseLogin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "login", url = "http://localhost:9005")
public interface LoginClient {

    @PostMapping(path = "/login/register", consumes = "application/json")
    public ResponseLogin addLogin(@RequestBody RequestLogin requestLogin);

}
