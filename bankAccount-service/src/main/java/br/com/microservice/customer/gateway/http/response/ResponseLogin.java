package br.com.microservice.customer.gateway.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseLogin {

    @JsonProperty("sucess")
    private String sucess;
    @JsonProperty("message")
    private String message;
}
