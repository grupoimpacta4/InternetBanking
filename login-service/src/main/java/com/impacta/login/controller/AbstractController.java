package com.impacta.login.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.google.gson.Gson;
import com.impacta.login.model.Mensagem;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

public abstract class AbstractController {

	Gson gson = new Gson();
	Mensagem msg ;

	@Resource
	@Autowired
	public Environment environment;

	@Resource @Autowired
	protected MessageSource messageSource;

	private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
	private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

	Logger LOGGER = Logger.getLogger(AbstractController.class); // Classse de Log

	/**
	 * Adds a new error message
	 *
	 * @param model  A model which stores the the error message.
	 * @param code   A message code which is used to fetch the correct message from
	 *               the message source.
	 * @param params The parameters attached to the actual error message.
	 * @return
	 */
	protected String addErrorMessage(String code, Object... params) {
		messageSource=messageSourceReturn();
		msg = new Mensagem();
		LOGGER.info("Adding error message with code: " + code + " and params: " + params);
		Locale current = Locale.US;
		LOGGER.info("Current locale is " + current);
		String localizedErrorMessage = messageSource.getMessage(code, params, current);
		LOGGER.info("Localized message is: " + localizedErrorMessage);
		msg.setStatus("false");
		msg.setMessage(localizedErrorMessage);
		return gson.toJson(msg);
	}


	/**
	 * Adds a new feedback message.
	 *
	 * @param model  A model which stores the feedback message.
	 * @param code   A message code which is used to fetch the actual message from
	 *               the message source.
	 * @param params The parameters which are attached to the actual feedback
	 *               message.
	 */
	protected String addFeedbackMessage(String code, Object... params) {
		messageSource=messageSourceReturn();
		msg = new Mensagem();
		LOGGER.info("Adding feedback message with code: " + code + " and params: " + params);
		Locale current = Locale.US;
		LOGGER.info("Current locale is " + current);
		String localizedFeedbackMessage = messageSource.getMessage(code, params, current);
		LOGGER.info("Localized message is: " + localizedFeedbackMessage);
		msg.setStatus("true");
		msg.setMessage(localizedFeedbackMessage);
		msg.setObject(params);
		return gson.toJson(msg);
	}


	/**
	 * This method should only be used by unit tests.
	 *
	 * @param messageSource
	 */
	protected void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public MessageSource messageSourceReturn() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("messages/messages_pt_BR");
		System.out.println("Mensagem ==== " + environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
		messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(
				environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));
		return messageSource;
	}
}