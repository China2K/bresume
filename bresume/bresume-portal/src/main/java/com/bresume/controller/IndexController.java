package com.bresume.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bresume.core.common.base.controller.BaseController;

@RequestMapping("/")
@Controller
public class IndexController extends BaseController {
	
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request) {
		return "/site/index.jsp";
	}

}
