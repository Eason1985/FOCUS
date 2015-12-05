/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fc.focus.selenium.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;

/**
 * 关于异常的工具类.
 * 
 * @author calvin
 */
public class Exceptions {

	private Exceptions() {
	}
	
	public static String getFullMessage(Throwable ex) {
		StringBuffer buf = new StringBuffer();

		if (StringUtils.isEmpty(ex.getMessage()))
			buf.append(ex.toString());
		else
			buf.append(ex.getMessage());

		if (ex.getCause() != null)
			buf.append(" caused by: [ ").append(getFullMessage(ex.getCause())).append(" ]");

		return buf.toString();
	}

	/**
	 * 将CheckedException转换为UncheckedException.
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 */
	public static String getStackTraceAsString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
}
