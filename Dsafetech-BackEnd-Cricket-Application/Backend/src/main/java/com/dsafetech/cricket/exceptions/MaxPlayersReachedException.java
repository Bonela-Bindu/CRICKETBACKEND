package com.dsafetech.cricket.exceptions;



public class MaxPlayersReachedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  MaxPlayersReachedException(String message) {
    	super(message);
    }
    
}
