package com.impacta.login.controller;

import com.impacta.login.model.LoginDao;
import com.impacta.login.model.LoginDto;
import com.impacta.login.model.UserLogged;
import com.impacta.login.service.JwtUserDetailsService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController extends AbstractController {

	public LoginController(Environment e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	protected static final String FEEDBACK_MESSAGE_KEY_LOGIN_CREATED = "feedback.message.login.created";
	protected static final String ERROR_MESSAGE_KEY_LOGIN_WAS_NOT_CREATED = "error.message.login.was.not.created";
	protected static final String ERROR_MESSAGE_KEY_EMAIL_EMPTY = "error.message.email.not.blank";
	protected static final String ERROR_MESSAGE_KEY_USERNAME_EMPTY = "error.message.username.not.blank";
	protected static final String ERROR_MESSAGE_KEY_PASSWORD_EMPTY = "error.message.password.not.blank";
	protected static final String ERROR_MESSAGE_KEY_USER_ALREADY_HAS_ACCOUNT = "error.message.user.has.account";
	protected static final String ERROR_MESSAGE_EMAIL_ALREADY_USED = "error.message.email.already.used";

	// LoginMessages
	protected static final String ERROR_MESSAGE_LOGIN_BAD_CREDENTIALS = "error.message.login.bad.credencials";
	protected static final String ERROR_MESSAGE_LOGIN_USER_NOT_FOUND = "error.message.login.user.not.found";
	protected static final String FEEDBACK_MESSAGE_LOGIN_SUCCESS = "error.message.login.sucess";
	protected static final String ERROR_MESSAGE_LOGIN_DEFAULT = "error.message.login.default";

	@PostMapping(path = "/authenticate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto authenticationRequest) throws Exception {
		try {
			LOGGER.info("Login process was started");

			userDetailsService.userNameAlreadExist(authenticationRequest.getUsername());

			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			String token;
			try {
				token = getToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			} catch (Exception e) {
				LOGGER.error(e);
				throw new Exception("Token service is not available");

			}
			LOGGER.info("Token was generated as " + token);
			UserLogged userLogged = new UserLogged();
			userLogged.setToken(token);
			userLogged.setUsername(userDetails.getUsername());
			LOGGER.info("User is logged");
			System.out.println("Token dois " + token);
			return new ResponseEntity<>(addFeedbackMessage(FEEDBACK_MESSAGE_LOGIN_SUCCESS, userLogged),
					HttpStatus.CREATED);

		} catch (UsernameNotFoundException useNotFound) {
			LOGGER.info("Username was not found");
			return new ResponseEntity<>(
					addErrorMessage(ERROR_MESSAGE_LOGIN_USER_NOT_FOUND, authenticationRequest.getUsername()),
					HttpStatus.UNAUTHORIZED);

		} catch (BadCredentialsException bad) {
			LOGGER.info("Credentials were wrong");
			return new ResponseEntity<>(
					addErrorMessage(ERROR_MESSAGE_LOGIN_BAD_CREDENTIALS, authenticationRequest.getUsername()),
					HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			System.out.println("Login failed " + e);
			e.printStackTrace();
			LOGGER.info("Login failed " + e);
			return new ResponseEntity<>(
					addErrorMessage(ERROR_MESSAGE_LOGIN_DEFAULT, authenticationRequest.getUsername()),
					HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveUser(@Valid @RequestBody LoginDto user) throws Exception {
		try {
			if (user.getUsername() == null || user.getUsername().isEmpty()) {
				return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_KEY_USERNAME_EMPTY, user.getUsername()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}
			if (user.getEmail() == null || user.getEmail().isEmpty()) {
				return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_KEY_EMAIL_EMPTY, user.getUsername()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}
			if (user.getPassword() == null || user.getPassword().isEmpty()) {
				return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_KEY_PASSWORD_EMPTY, user.getUsername()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			List<LoginDao> listaEmail = userDetailsService.searchingByEmail(user);
			if (listaEmail.size() > 0) {
				return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_EMAIL_ALREADY_USED, user.getUsername()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

			if (userDetailsService.userNameAlreadExist(user.getUsername())) {

				ResponseEntity<?> repEntity = new ResponseEntity<>(
						addErrorMessage(ERROR_MESSAGE_KEY_USER_ALREADY_HAS_ACCOUNT, user.getUsername()),
						HttpStatus.INTERNAL_SERVER_ERROR);
				return repEntity;

			}

			userDetailsService.save(user);
			LOGGER.info("Login was created");

			try {
				createUser(user.getUsername(), user.getPassword(), getToken("user", "pass"));
				LOGGER.info("Oath Login was created");
			} catch (Exception e) {

				System.out.println("---------");
				System.out.println("Login failed " + e);
				e.printStackTrace();
				LOGGER.error("It was not possivel to create an accoun right now");
				throw new Exception("It was not possivel to create an accoun right now");
			}

			return new ResponseEntity<>(addFeedbackMessage(FEEDBACK_MESSAGE_KEY_LOGIN_CREATED, user.getUsername()),
					HttpStatus.CREATED);

		} catch (Exception e) {
			System.out.println("Login failed " + e);
			e.printStackTrace();
			LOGGER.error("Error infos " + e);
			return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_KEY_LOGIN_WAS_NOT_CREATED, user.getUsername()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void createUser(String username, String password, String token) throws Exception {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://localhost:9007/oauth/register");
		httpPost.addHeader("Authorization", "Bearer " + token);
		httpPost.addHeader("Content-Type", "application/json");
		String body = "{\n  \"username\" :\"" + username + "\", \n   \"enabled\":1,\n    \"password\" : \"" + password
				+ "\" \n }";
		System.out.println("body =  " + body);
		StringEntity stringEntity = new StringEntity(body);
		httpPost.setEntity(stringEntity);
		System.out.println("url =  " + httpPost.getURI());

		try {
			org.apache.http.HttpEntity httpResponse = httpClient.execute(httpPost).getEntity();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getContent()));
			String inputLine = "User was not created";
			String acess_token = "";

			while ((inputLine = in.readLine()) != null) {
				acess_token = inputLine;
				System.out.println(" linha " + inputLine);
				if (inputLine.equals("User was not created"))
					throw new Exception("User was not created");
			}
			in.close();
			if (acess_token.equals("User was not created")) {
				throw new Exception("User was not created");
			}
			System.out.println("Token dois " + acess_token);

			System.out.println(httpResponse.getContent());
		} catch (HttpHostConnectException httpex) {
			LOGGER.error("It was impossible to connect to Oath service");
			LOGGER.error("Error infos " + httpex);
			throw httpex;
		}
	}

	private String getToken(String user, String password) throws Exception {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://localhost:9007/oauth/token");
		httpPost.addHeader("Authorization", "Basic Y2xpZW50SWQ6c2VjcmV0");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("grant_type", "password"));
		params.add(new BasicNameValuePair("username", user));
		params.add(new BasicNameValuePair("password", password));
		System.out.println("user " + user);
		System.out.println("password " + password);
		UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, "UTF-8");
		httpPost.setEntity(ent);
		System.out.println("url =  " + httpPost.getURI());
		try {
			org.apache.http.HttpEntity httpResponse = httpClient.execute(httpPost).getEntity();

			BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getContent()));
			String inputLine = "";
			String body = "[\n ";
			while ((inputLine = in.readLine()) != null) {
				System.out.println(" Linha " + inputLine);
				body = body + inputLine;
			}
			body = body + "]";
			JSONArray ja = new JSONArray(body); // objetos é o meu o array json
			String tokenizen = "";
			for (int i = 0; i < ja.length(); i++) {
				JSONObject obj1 = ja.getJSONObject(i);
				if (obj1.has("access_token"))
					tokenizen = obj1.getString("access_token");
				else {
					throw new Exception("Token was not generated");
				}
			}
			if (tokenizen.isEmpty()) {
				throw new Exception("Token was not generated");
			}
			System.out.println("access_token got " + tokenizen);
			in.close();
			return tokenizen;
		} catch (HttpHostConnectException httpex) {
			LOGGER.error("It was impossible to connect to Oath service");
			LOGGER.error("Error infos " + httpex);
			throw httpex;
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			System.out.println(" \n \n Senha code = " + new BCryptPasswordEncoder().encode(password) + " \n \n");
			LoginDao user = userDetailsService.getUserByUsername(username);
			System.out.println("senha enviada = " + password);
			System.out.println("senha banco = " + user.getPassword());
			boolean samePass = BCrypt.checkpw(password.trim(), user.getPassword().trim());
			System.out.println(" samePass " + samePass);
			if (samePass) {
				System.out.println(" Passei samePass " + samePass);
				authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));

			} else
				throw new BadCredentialsException("INVALID_CREDENTIALS");
		} catch (DisabledException e) {
			System.out.println("User disable");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			System.out.println("Invalid_Credential");
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}
}
