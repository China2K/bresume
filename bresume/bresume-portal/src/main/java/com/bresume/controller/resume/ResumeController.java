package com.bresume.controller.resume;

import java.lang.reflect.Field;
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
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.common.utils.CommonUtils;
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
		Page<Resume> result = resumeService.findPage(pageable, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));

		model.addAttribute("resumes", result.getContent());

		return "site/resumes.jsp";
	}

	@RequestMapping("/buildResume.do")
	public String bulidResume(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "template", required = false) String sn,
			Model model) {
		System.out.println(id);
		/*
		 * if(CommonUtils.isNotEmpty("id")){ //编辑 Resume resume=
		 * resumeService.findOne(id); //TODO }
		 */

		Template template = templateService.findUniqueBy("sn", sn);

		List<ResumeItem> allResumeItems = (List<ResumeItem>) resumeItemService
				.findAll(new Sort(Direction.ASC, "order"));

		model.addAttribute("template", template);
		model.addAttribute("allResumeItems", allResumeItems);

		return "site/resume.jsp";
	}

	@RequestMapping("/resumeItem.do")
	public String reusmeItem(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "itemSn", required = true) String sn,
			Model model) {
		ResumeItemType resumeItem = ResumeItemType.fromSn(sn);
		if (CommonUtils.isNotEmpty(id) && CommonUtils.isNotEmpty(sn)) {
			List objItems = resumeItemService.findResumeItem(resumeItem, id);
		}
		try {
			Class clazz = resumeItem.getClazz();
			Object obj = clazz.newInstance();

			Field field;
			try {
				field = clazz.getDeclaredField("resume");
				field.setAccessible(true);
				field.set(obj, new Resume());
				field.setAccessible(false);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

			model.addAttribute(resumeItem.getName(), obj);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return "site/item/" + ResumeItemType.fromSn(sn).getPage();
	}

}
