package com.fc.focus.selenium.utils.exception;


public class FTException extends RuntimeException {
	private FTMessage message;
	
	private String errorCode;

	public FTException() {
		super();
	}
	
	public FTException(String message) {
		super(message);
	}

	public FTException(String message, Throwable cause) {
		super(message, cause);
	}

	public FTException(FTMessage message, Throwable cause) {
		super(cause);

		this.message = message;
	}
	
	public FTException(String messageCode, Object[] messageArgs, Throwable cause) {
		super(cause);
		
		this.message = new FTMessage(messageCode, messageArgs);
	}

	public FTException(FTMessage message) {
		this.message = message;
	}

	public FTException(Throwable cause) {
		super(cause);
	}

	public FTMessage getBafMessage() {
		return message;
	}

	public void setBafMessage(FTMessage message) {
		this.message = message;
	}

	//	public String getMessage() {
	//		if (message != null)
	//			return BafContext.getCurrent().getMessage(message.getKey(), message.getArgs());
	//		else
	//			return super.getMessage();
	//	}

	public String getFullMessage() {
		return Exceptions.getFullMessage(this);
	}

	private static final long serialVersionUID = 1L;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errno) {
		this.errorCode = errno;
	}

}
