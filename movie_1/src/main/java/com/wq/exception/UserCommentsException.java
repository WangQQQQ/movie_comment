package com.wq.exception;


/**
 * @author kyrieqing[wangq_0228@163.com]
 */
@SuppressWarnings("serial")
public class UserCommentsException extends RuntimeException {

	public UserCommentsException() {
		super();
	}

	public UserCommentsException(String message) {
		super(message);
	}

	public UserCommentsException(String message, Throwable cause) {
		super(message, cause);
	}
}
