package com.baymotors.exceptions;

public class DuplicateCustomerException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateCustomerException(String message) {
        super(message);
    }
}
