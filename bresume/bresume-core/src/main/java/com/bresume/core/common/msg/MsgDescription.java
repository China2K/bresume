package com.bresume.core.common.msg;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.bresume.core.common.utils.CommonUtils;

public class MsgDescription 
{
	private static final Map<String,String>	code2KeyMap	= new HashMap<String, String>();

	private static final Logger logger = Logger.getLogger(MsgDescription.class);
	
	private static final Properties msgProperties = new Properties();
	
	static
	{
		Object o = new MsgCode();
		Field[] fields = o.getClass().getFields();
		try
		{
			for (int i = 0; i < fields.length; i++)
			{
				String fieldName = new String();
				fieldName = fields[i].getName().replace("_", ".").toLowerCase();
				code2KeyMap.put((String)fields[i].get(o), fieldName);
			}
		} catch (Exception e)
		{
			logger.error("MsgDescription load msgCode error",e);
		}
	}
	
	static{
		InputStream in = null;
		try
		{
			in = MsgDescription.class.getClassLoader().getResourceAsStream("message/messages.properties");
			msgProperties.load(in);
		} catch (IOException e)
		{
			logger.error("Load messages.properties error", e);
		}finally{
			if(in != null){
				try
				{
					in.close();
				} catch (IOException e)
				{
					logger.error("Close inputStream of message.properties error", e);
				}
			}
		}
	}
	
	public static String getMsgDesc(String code, Object... params)
	{
		String result = "未知信息";
		String key = code2KeyMap.get(code);
		if (CommonUtils.isNotEmpty(key))
		{
			String msg = msgProperties.getProperty(key);
			if(CommonUtils.isNotEmpty(msg)){
				result =  MessageFormat.format(msg, params);
			}
		} 
		return result;
	}

	public static String getMsgDesc(String code)
	{
		String result = "未知信息";
		String key = code2KeyMap.get(code);
		if (CommonUtils.isNotEmpty(key))
		{
			result = msgProperties.getProperty(key);
		} 
		return result;
	}
	
	public static String getMsgByKey(String key){
		return msgProperties.getProperty(key);
	}
}
