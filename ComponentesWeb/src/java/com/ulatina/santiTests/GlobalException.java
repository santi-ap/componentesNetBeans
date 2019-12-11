/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.santiTests;

/**
 *
 * @author santialfonso
 */
public class GlobalException extends Exception {
    public GlobalException() {
		super();
	}

	public GlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GlobalException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalException(String message) {
		super(message);
	}

	public GlobalException(Throwable cause) {
		super(cause);
	}
}
