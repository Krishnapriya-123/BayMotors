package com.baymotors.exceptions;

public class InvalidCustomerException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCustomerException(String message) {
        super(message);
    }
}
