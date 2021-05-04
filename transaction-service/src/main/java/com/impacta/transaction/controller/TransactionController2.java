package com.impacta.transaction.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.impacta.transaction.model.Balance;
import com.impacta.transaction.model.Extrato;
import com.impacta.transaction.model.ResponseDto;
import com.impacta.transaction.model.TransactionDTO;
import com.impacta.transaction.model.TransactionDao;
import com.impacta.transaction.model.TransferDTO;
import com.impacta.transaction.service.TransactionDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class TransactionController2 {

	public static void main(String[] args) throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate(); // 1
		String url = "http://localhost:9011/v1/customers/12345678901"; // 2
		ResponseEntity<ResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, request, ResponseDto.class);

		System.out.println("executando código");
		long account = returnAccount("12345678901", 1); 
		long accountP = returnAccount("12345678901", 2); 
		System.out.println("Account "+account);
		System.out.println("Account "+accountP);
		returnAccountPerUser("12345678901");
		System.out.println(returnAccountPerUser("12345678901").getCurrentAccount().getAccountNumber());
		System.out.println(returnAccountPerUser("12345678901").getSavingsAccount().getAccountNumber());
		
	}

	public static long returnAccount(String cpf, int type) throws Exception {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity request = new HttpEntity<>(requestHeaders);
		RestTemplate restTemplate = new RestTemplate(); // 1
		String url = "http://localhost:9011/v1/customers/12345678901"; // 2
		ResponseEntity<ResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, request, ResponseDto.class);

		if (type == 1) {
			if (response.getBody().getCurrentAccount().getStatus() == false) {
				return -1;
			}
			String accountNumber = response.getBody().getCurrentAccount().getAccountNumber().toString()
					+ response.getBody().getCurrentAccount().getCheckDigit();
			System.out.println(accountNumber);
		}
		if (type == 2) {
			if (response.getBody().getSavingsAccount().getStatus() == false) {
				System.out.println("Retornou");
				return -1;
			}
			String accountNumber = response.getBody().getSavingsAccount().getAccountNumber().toString()
					+ response.getBody().getSavingsAccount().getCheckDigit();
			System.out.println(accountNumber);
			return Integer.parseInt(accountNumber);
		}

		return 0;
	}
	
	public static ResponseDto returnAccountPerUser(String cpf) throws Exception {

 		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity request = new HttpEntity<>(requestHeaders);
			RestTemplate restTemplate = new RestTemplate(); // 1
			String url = "http://localhost:9011/v1/customers/12345678901"; // 2
			ResponseEntity<ResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, request, ResponseDto.class);

 
			ResponseDto responseDto =  response.getBody();
			System.out.println(responseDto.getEmail());
			System.out.println(responseDto.getCurrentAccount().getAccountNumber());
			System.out.println(responseDto.getCurrentAccount().getCheckDigit());
			System.out.println(responseDto.getSavingsAccount().getAccountNumber());
			System.out.println(responseDto.getSavingsAccount().getCheckDigit());

			return responseDto;

		} catch (Exception Ex) {
			throw Ex;
		}
	}
	

}