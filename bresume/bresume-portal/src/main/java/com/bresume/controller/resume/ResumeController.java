package com.bresume.controller.resume;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.ResumeItem;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.service.resume.IResumeItemService;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;

@RequestMapping("/resume")
@Controller
public class ResumeController extends BaseController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IResumeItemService resumeItemService;

	@RequestMapping("/startBulidResume.do")
	public String contact(HttpServletRequest request, Model model) {

		Pageable page = new PageRequest(0, 10, new Sort(Direction.ASC, "order"));
		Page<Template> result = templateService.findPage(page, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));
		List<Template> list = result.getContent();
		model.addAttribute("templates", list);

		return "site/templates.jsp";
	}

	@RequestMapping("/resumes.do")
	public String listResume(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			Model model) {

		Pageable pageable = new PageRequest(page, limit, new Sort(
				Direction.ASC, "order"));
		SearchBean[] sbs = new SearchBean[2];
		sbs[0] = new SearchBean("status", CommonStatus.ACTIVE.getCode() + "",
				"=");
		Page<Resume> result = resumeService.findPage(pageable, sbs);

		model.addAttribute("resumes", result.getContent());

		return "site/resumes.jsp";
	}

	@RequestMapping("/bulidResume.do")
	public String bulidResume(HttpServletRequest request,
			@RequestParam(value = "templateSn", required = true) String sn,
			Model model) {

		Template template = templateService.findUniqueBy("sn", sn);
		

		List<ResumeItem> allResumeItems = resumeItemService.findAll(new Sort(
				Direction.ASC, "order"), new SearchBean("status",
				CommonStatus.ACTIVE.getCode() + "", "="));
		
		model.addAttribute("template", template);
		model.addAttribute("allResumeItems", allResumeItems);

		return "site/resume.jsp";
	}

}
