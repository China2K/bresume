package com.bresume.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bresume.core.common.base.controller.AdminController;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;
import com.bresume.core.service.user.IContactService;

@RequestMapping("/")
@Controller
public class IndexController extends AdminController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IContactService constactService;
	
	@RequestMapping("/jsptransit.do")
	public String jspTransfer(HttpServletRequest request)
	{
		String url = request.getParameter("url");
		return url;
	}
	

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, Model model) {

		return "/page/index.jsp";
	}

	@RequestMapping("/dashboard.do")
	public String dashboard(HttpServletRequest request, Model model) {
		return "/page/dashboard.jsp";
	}

	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, Model model) {
		return "/page/login.jsp";
	}

}
