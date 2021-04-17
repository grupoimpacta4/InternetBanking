package br.com.microservice.customer.gateway.http;

import br.com.microservice.customer.model.Customer;
import br.com.microservice.customer.model.CustomerDto;
import br.com.microservice.customer.model.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "Customer Controller", description = "Operations pertaining to customer in Online")
@RestController
@RequestMapping("/v1")
public class CustomerController extends AbstractController {

	public CustomerController(Environment e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private br.com.microservice.customer.gateway.service.CustomerService customerService;

	protected static final String FEEDBACK_MESSAGE_KEY_LOGIN_CREATED = "feedback.message.person.created";
	protected static final String ERROR_MESSAGE_KEY_LOGIN_WAS_NOT_CREATED = "error.message.person.was.not.created";
	protected static final String ERROR_MESSAGE_KEY_EMAIL_EMPTY = "error.message.email.not.blank";
	protected static final String ERROR_MESSAGE_KEY_USERNAME_EMPTY = "error.message.username.not.blank";
	protected static final String ERROR_MESSAGE_KEY_PASSWORD_EMPTY = "error.message.password.not.blank";
	protected static final String ERROR_MESSAGE_KEY_USER_ALREADY_HAS_ACCOUNT = "error.message.user.has.account";
	protected static final String ERROR_MESSAGE_EMAIL_ALREADY_USED = "error.message.email.already.used";

	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Save new customer", response = ResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully save customer"),
			@ApiResponse(code = 400, message = "Customer already registered") })
	@ResponseBody
	@PostMapping(path="/customers", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> saveCustomer(@RequestBody CustomerDto customer) {

		try {
			if (customer.getDocument() == null || customer.getDocument().isEmpty()) {
				return new ResponseEntity<String>(
						addErrorMessage(ERROR_MESSAGE_KEY_USERNAME_EMPTY, customer.getDocument()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}
			if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
				return new ResponseEntity<String>(
						addErrorMessage(ERROR_MESSAGE_KEY_EMAIL_EMPTY, customer.getDocument()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}
			if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
				return new ResponseEntity<String>(
						addErrorMessage(ERROR_MESSAGE_KEY_PASSWORD_EMPTY, customer.getDocument()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			List<Customer> listaEmail = customerService.searchingByEmail(customer);
			if (listaEmail.size() > 0) {
				return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_EMAIL_ALREADY_USED, customer.getDocument()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

			if (customerService.userNameAlreadExist(customer)) {

				return new ResponseEntity<>(
						addErrorMessage(ERROR_MESSAGE_KEY_USER_ALREADY_HAS_ACCOUNT, customer.getDocument()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

			
			try {
				ResponseDto response = customerService.save(customer);
				return new ResponseEntity<>(addFeedbackMessage(FEEDBACK_MESSAGE_KEY_LOGIN_CREATED, response.getDocument(),response),
						HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_KEY_LOGIN_WAS_NOT_CREATED, customer.getDocument()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} catch (Exception e) {
			return new ResponseEntity<>(addErrorMessage(ERROR_MESSAGE_KEY_LOGIN_WAS_NOT_CREATED, customer.getDocument()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "View customer", response = ResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved customer"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@ResponseBody
	@GetMapping(path = "/customers/{document}", consumes = "application/json", produces = "application/json")
	public ResponseDto findByDocumentCustomer(@PathVariable String document) {
		log.info("Customers find");
		return customerService.findByDocument(document);
	}

}
