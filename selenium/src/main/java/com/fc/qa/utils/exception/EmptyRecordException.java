package com.fc.qa.utils.exception;


public class EmptyRecordException extends RuntimeException {
	private FTMessage message;
	
	private String errorCode;

	public EmptyRecordException() {
		super();
	}
	
	public EmptyRecordException(String message) {
		super(message);
	}

	public EmptyRecordException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyRecordException(FTMessage message, Throwable cause) {
		super(cause);

		this.message = message;
	}
	
	public EmptyRecordException(String messageCode, Object[] messageArgs, Throwable cause) {
		super(cause);
		
		this.message = new FTMessage(messageCode, messageArgs);
	}

	public EmptyRecordException(FTMessage message) {
		this.message = message;
	}

	public EmptyRecordException(Throwable cause) {
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
