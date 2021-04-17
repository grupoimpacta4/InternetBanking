package br.com.microservice.customer.gateway.http;

 

public class Message {
	

	private String sucess;
    private String message;
    private Object data;
    
	 
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return sucess;
	}
	public void setStatus(String sucess) {
		this.sucess = sucess;
	}
	
	@Override
	public String toString() {
	   return "Sucess: " + this.sucess +
     "Mensagem: " + this.message;}
	public Object getObject() {
		return data;
	}
	public void setObject(Object data) {
		this.data = data;
	}
	 
}
