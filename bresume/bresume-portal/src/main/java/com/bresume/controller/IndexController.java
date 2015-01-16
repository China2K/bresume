package com.bresume.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.constant.enums.ContactStatus;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.model.entity.user.Contact;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;
import com.bresume.core.service.user.IContactService;

@RequestMapping("/")
@Controller
public class IndexController extends BaseController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IContactService constactService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {

		List<Template> hotTemplates = templateService.findHostTemplates();

		List<Resume> hotResumes = resumeService.findHostResumes();

		model.addAttribute("hotTemplates", hotTemplates);
		model.addAttribute("hotResumes", hotResumes);
		return "/site/index.jsp";
	}
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		return "/site/login.jsp";
	}

	@RequestMapping("/contact")
	public @ResponseBody JSONObject contact(
			HttpServletRequest request,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "cellPhone", required = false) String cellPhone,
			Model model) {

		Contact contact = new Contact();
		contact.setCellPhone(cellPhone);
		contact.setCreatedTime(new Date());
		contact.setName(name);
		contact.setMessage(message);
		contact.setStatus(ContactStatus.INTITAL.getCode());
		constactService.save(contact);
		return this.toJSONResult(true, "感谢您的宝贵意见,我会最快时间阅读并给您回复!");
	}

	@RequestMapping("/templates")
	public String templates(HttpServletRequest request, Model model) {

		Pageable page = new PageRequest(0, 10, new Sort(Direction.ASC, "order"));
		Page<Template> result = templateService.findPage(page, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));
		List<Template> list = result.getContent();
		model.addAttribute("templates", list);

		return "site/templates.jsp";
	}

	@RequestMapping("/resumes")
	public String listResume(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			Model model) {

		Pageable pageable = new PageRequest(page, limit, new Sort(
				Direction.ASC, "order"));
		Page<Resume> result = resumeService.findPage(pageable, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));

		model.addAttribute("resumes", result.getContent());

		return "site/resumes.jsp";
	}

}
