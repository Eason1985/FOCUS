package com.fc.qa.utils.exception;

public class FTMessage {
	/** The message key to use. */
	protected String key;
	
	/** The message arguments to use. */
	protected Object[] args;

	public FTMessage(String key) {
		this.key = key;
	}

	public FTMessage(String key, Object arg) {
		this.key = key;
		this.args = new Object[] { arg };
	}

	public FTMessage(String key, Object[] args) {
		this.key = key;
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
