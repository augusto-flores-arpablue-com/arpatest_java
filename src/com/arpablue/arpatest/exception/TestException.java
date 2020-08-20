/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.arpatest.exception;

/**
 *
 * @author user-pc
 */
public abstract class TestException extends RuntimeException {
    	public TestException(String message, Throwable cause) {
		super(message, cause);
	}
    	public TestException(String message) {
		super(message);
	}
        /**
         * It specify the code of the exception.
         * @return 
         */
        public abstract int getExceptionCode();
        

    
}
