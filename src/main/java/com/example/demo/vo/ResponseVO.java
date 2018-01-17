package com.example.demo.vo;

public class ResponseVO<T> {
	
	private String statusCode;
	private T valueObject;

	public ResponseVO() {
    }

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public T getValueObject() {
		return valueObject;
	}

	public void setValueObject(T valueObject) {
		this.valueObject = valueObject;
	}
	
	
	
}
