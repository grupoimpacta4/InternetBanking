package com.impacta.login.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.google.gson.Gson;
import com.impacta.login.model.Mensagem;
 
public abstract class AbstractController {

	Logger LOGGER = Logger.getLogger(AbstractController.class); // Classse de Log

	 
	Gson gson = new Gson();
	Mensagem msg ;

	@Resource @Autowired
	protected MessageSource messageSource;

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
		msg= new Mensagem(); 	
		LOGGER.info("Adding error message with code: " + code + " and params: " + params);
		Locale current = LocaleContextHolder.getLocale();
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
		msg= new Mensagem();
		LOGGER.info("Adding feedback message with code: " + code + " and params: " + params);
		Locale current = LocaleContextHolder.getLocale();
		LOGGER.info("Current locale is " + current);
		String localizedFeedbackMessage = messageSource.getMessage(code, params, current);
		LOGGER.info("Localized message is: " + localizedFeedbackMessage);
		msg.setStatus("true");
		msg.setMessage(localizedFeedbackMessage);
		System.out.println("Saindo mensagem de erro");	
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
}