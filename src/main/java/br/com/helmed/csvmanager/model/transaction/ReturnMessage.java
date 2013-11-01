package br.com.helmed.csvmanager.model.transaction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReturnMessage {
	
	@XmlElement(name = "ResponseCode")
	private String responseCode;

	@XmlElement(name = "Message")
	private String message;
	
	public ReturnMessage() {
	}
	
	public ReturnMessage(String code, String message) {
		this.responseCode = code;
		this.message = message;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ReturnMessage [message=" + message + ", responseCode="
				+ responseCode + "]";
	}
	
	

}
