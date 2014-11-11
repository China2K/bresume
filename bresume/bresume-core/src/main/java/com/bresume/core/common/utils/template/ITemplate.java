package com.bresume.core.common.utils.template;

/**
 *
 * @author 2k
 */
public interface ITemplate
{
	/**
	 * 根据模板转换成字符串
	 * @param       
	 * @return     
	 * @throws
	 */
	String processTemplate2String(String template, Object... args);
}
