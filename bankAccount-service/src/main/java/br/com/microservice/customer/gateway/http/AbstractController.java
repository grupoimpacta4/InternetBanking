package br.com.microservice.customer.gateway.http;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource; 
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource; 
import org.springframework.core.env.Environment;

import com.google.gson.Gson; 

public abstract class AbstractController {

	@Resource
	@Autowired
	public Environment environment;

	@Resource
	@Autowired
	public MessageSource messageSource;

	private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
	private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

	Logger LOGGER = Logger.getLogger(AbstractController.class); // Classse de Log

	public AbstractController(Environment e) { 
	}

	
	 
	public MessageSource messageSourceReturn() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("messages/messages");
		System.out.println("Mensagem ==== " + environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
		messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(
				environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));
		return messageSource;
	}

	Gson gson = new Gson();
	Message msg;

	protected String addErrorMessage(String code, Object... params) {
		messageSource=messageSourceReturn();
		msg = new Message();
		LOGGER.info("Adding error message with code: " + code + " and params: " + params);
		Locale current = LocaleContextHolder.getLocale();
		LOGGER.info("Current locale is " + current);
		String localizedErrorMessage = messageSource.getMessage(code, params, current);
		LOGGER.info("Localized message is: " + localizedErrorMessage);
		msg.setStatus("false");
		msg.setMessage(localizedErrorMessage);
		return gson.toJson(msg);
	}

	protected String addFeedbackMessage(String code, Object... params) {
		messageSource=messageSourceReturn();
		msg = new Message();
		LOGGER.info("Adding feedback message with code: " + code + " and params: " + params);
		Locale current = LocaleContextHolder.getLocale();
		LOGGER.info("Current locale is " + current);
		String localizedFeedbackMessage = messageSource.getMessage(code, params, current);
		LOGGER.info("Localized message is: " + localizedFeedbackMessage);
		msg.setStatus("true");
		msg.setMessage(localizedFeedbackMessage); 
		msg.setObject(params);
		return gson.toJson(msg);
	}

	void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}