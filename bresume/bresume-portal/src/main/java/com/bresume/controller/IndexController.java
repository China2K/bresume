package com.bresume.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.constant.enums.ContactStatus;
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

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, Model model) {

		List<Template> hotTemplates = templateService.findHostTemplates();

		List<Resume> hotResumes = resumeService.findHostResumes();

		model.addAttribute("hotTemplates", hotTemplates);
		model.addAttribute("hotResumes", hotResumes);
		return "/site/index.jsp";
	}

	@RequestMapping("/contact.do")
	public @ResponseBody
	JSONObject contact(HttpServletRequest request, 
			@RequestParam(value="email",required=true) String email,
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="message",required=true) String message,
			@RequestParam(value="cellPhone",required=false) String cellPhone,
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

}
