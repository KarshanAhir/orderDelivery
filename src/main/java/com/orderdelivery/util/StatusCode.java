package com.orderdelivery.util;

public enum StatusCode {
	UNASSIGNED(0, "UNASSIGNED"), ASSIGNED(1, "ASSIGNED"), SUCCESS(2, "SUCCESS"), TAKEN(3, "TAKEN");

	private final int code;
	private final String message;

	StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
