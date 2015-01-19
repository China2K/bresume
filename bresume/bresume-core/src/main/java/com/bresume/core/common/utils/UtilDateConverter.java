package com.bresume.core.common.utils;

import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class UtilDateConverter implements Converter {
	private Log log = LogFactory.getLog(this.getClass());

	public UtilDateConverter() {

		this.defaultValue = null;
		this.useDefault = false;
	}

	public UtilDateConverter(String defaultValue) {

		this.defaultValue = defaultValue;
		this.useDefault = true;
	}

	private String defaultValue = null;

	private boolean useDefault = true;

	@Override
	public Object  convert(Class type, Object value) {
		System.out.println("tttttttttt");

		if (value == null || "".equals(value)) {
			if (useDefault) {
				return (defaultValue);
			} else {
				return null;
			}
		}

		if (value instanceof java.util.Date) {
			try {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date.class.cast(value));
			} catch (Exception e) {
				log.error("convert error ocured.", e);
				if (useDefault) {
					return (defaultValue);
				} else {
					return null;
				}
			}
		}
		return value.toString();
	}
}