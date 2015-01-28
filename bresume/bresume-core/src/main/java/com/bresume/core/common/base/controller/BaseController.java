package com.bresume.core.common.base.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.msg.MsgDescription;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.GeneralUtils;
import com.bresume.core.common.utils.json.JsonHelper;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.common.utils.security.Encrypt;

/**
 * Controller基类，所有的Controller必须要继承此类
 * 
 */
public abstract class BaseController extends SimpleFormController {
	protected Logger LOGGER = Logger.getLogger(this.getClass());

	public static final String DEFAULT_JSON_DATA = "data";

	public static final String DEFAULT_JSON_TOTAL_PROPERTY = "totalCount";

	public static final String DEFAULT_JSON_MESSAGE = "message";

	public static final String DEFAULT_JSON_SUCCESS = "success";
	
	public static final String DEFAULT_JSON_ID = "id";
	
	public static final String DEFAULT_JSON_ID_TOTALPAGES="totalpages";
	
	public static final String DEFAULT_JSON_ID_CURRPAGE="currpage";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	protected String getMessage(CoreException e) {
		return MsgDescription.getMsgDesc(e.getErrorCode());
	}

	protected String getMessage(CoreException e, Object... params) {
		return MsgDescription.getMsgDesc(e.getErrorCode(), params);
	}
	
	
	protected JSONObject toJSONResult(long count,List data,int pageSize,int currpage) {
		pageSize=pageSize==0?10:pageSize;
		currpage=currpage==0?1:currpage;
		
		long totalPage = count/pageSize+(count % pageSize==0?0:1);
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_DATA, data);
		result.put(DEFAULT_JSON_ID_TOTALPAGES, totalPage);
		result.put(DEFAULT_JSON_ID_CURRPAGE, currpage);
		return result;
	}

	protected JSONObject toJSONResult(long count, List data) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_TOTAL_PROPERTY, count);
		result.put(DEFAULT_JSON_DATA, data);
		return result;
	}

	protected JSONObject toJSONResult(boolean success, String message) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		if (CommonUtils.isNotEmpty(message)) {
			result.put(DEFAULT_JSON_MESSAGE, message);
		}
		return result;
	}

	protected JSONObject toJSONResult(boolean success, String message, String id) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		if (CommonUtils.isNotEmpty(message)) {
			result.put(DEFAULT_JSON_MESSAGE, message);
		}
		if (CommonUtils.isNotEmpty(id)) {
			result.put(DEFAULT_JSON_ID, id);
		}
		return result;
	}

	protected JSONObject toJSONResult(boolean success) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		return result;
	}

	protected JSONObject toJSONResult(boolean success, Object data) {
		JSONObject result = new JSONObject();
		result.put(DEFAULT_JSON_SUCCESS, success);
		result.put(DEFAULT_JSON_DATA, data);
		return result;
	}

	protected String toJSONResult(Object data) {
		return JsonHelper.toJSONString(data);
	}

	protected String toStringResultFromJson(boolean success, Object data) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(DEFAULT_JSON_DATA, data);
		map.put(DEFAULT_JSON_SUCCESS, success);

		return JsonHelper.toJSONString(map);
	}

	protected Pageable convert2Pageable(int start, int limit) {
		if (limit == 0) {
			limit = Integer.MAX_VALUE;
		}
		Pageable pageable = new PageRequest(start / limit, limit);
		return pageable;
	}

	/**
	 * 根据前台的param参数，返回searchbean对象
	 * 
	 * @param param
	 * @return
	 */
	protected SearchBean[] convert2SearchBean(String param) {
		if (GeneralUtils.isNullOrZeroLenght(param)) {
			return new SearchBean[] {};
		}

		String[] paramStrs = param.split(",");

		List<SearchBean> list = new ArrayList<SearchBean>();
		for (String paramStr : paramStrs) {
			String[] search = paramStr.split(":");
			// 如果没有填写值，则不生成searchbean
			if (search.length < 3 || GeneralUtils.isNullOrZeroLenght(search[1])) {
				continue;
			}

			SearchBean searchBean = new SearchBean(search[0], search[1],
					search[2]);
			list.add(searchBean);
		}
		return list.toArray(new SearchBean[] {});
	}
	
	protected List<SearchBean> convert2SearchBean(HttpServletRequest request) {
		String query = request.getParameter("query");
		List<SearchBean> list = new ArrayList<SearchBean>();
		if(CommonUtils.isNotEmpty(query)){
			String[] paramStrs = query.split(",");
			for (String paramStr : paramStrs) {
				String[] search = paramStr.split(":");
				// 如果没有填写值，则不生成searchbean
				if (search.length < 2 || GeneralUtils.isNullOrZeroLenght(search[1])) {
					continue;
				}
				SearchBean searchBean = new SearchBean(search[0], search[1],"like");
				list.add(searchBean);
			}
		}
		return list;
	}

	public abstract Object getCurrentLoginUser();
	
	public abstract String getCurrentUserId() ;
	
	
	public String getUploadAuthInfo(String userId,String userPSW){
		String info = userId+"_"+userPSW;
		return Encrypt.encryptSSO(info, IConstants.HELLO_WORD);
	}
	
}
