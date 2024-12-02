package com.baymotors.exceptions;

public class ManufacturerAlreadyExistsException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManufacturerAlreadyExistsException(String message) {
        super(message);
    }
}
