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
		System.out.println("executando código");
		long account= returnAccount("32345678952", 2);
		System.out.println("Account = "+account);
		System.out.println("executando código 1");
		TransactionController.returnAccountPerUser("32345678952");
	}

	public static long returnAccount(String cpf, int type) throws Exception {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet("http://localhost:9191/v1/customers/" + cpf);
		System.out.println("url =  " + httpget.getURI());
		try {
			org.apache.http.HttpEntity httpResponse = httpClient.execute(httpget).getEntity();

			BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getContent()));
			String inputLine = "";
			String body = "";
			while ((inputLine = in.readLine()) != null) {
				body = body + inputLine;
			}
			System.out.println("body " + body);
			if(body.contains("Customer not found")) {
				return 0;
			}
			
			ObjectMapper mapper = new ObjectMapper();
			ResponseDto map = mapper.readValue(body, ResponseDto.class);
			System.out.println(map.getEmail());
			System.out.println(map.getCurrentAccount().getAccountNumber());
			System.out.println(map.getCurrentAccount().getCheckDigit());

			if (type == 1) {
				if (map.getCurrentAccount().getStatus() == false) {
					return -1;
				}
				String accountNumber = map.getCurrentAccount().getAccountNumber().toString()+map.getCurrentAccount().getCheckDigit();
				System.out.println(accountNumber);
			}
			if (type == 2) {
				if (map.getSavingsAccount().getStatus() == false) {
					System.out.println("Retornou");
					return -1;
				}
				String accountNumber = map.getCurrentAccount().getAccountNumber().toString()+map.getCurrentAccount().getCheckDigit();
				System.out.println(accountNumber);
				return Integer.parseInt(accountNumber);
			}

		} catch (HttpHostConnectException httpex) {
			throw httpex;
		}
		return 0;
	}

}
