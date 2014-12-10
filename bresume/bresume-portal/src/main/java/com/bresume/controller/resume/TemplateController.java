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

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.service.resume.ITemplateService;

@RequestMapping("/template")
@Controller
public class TemplateController extends BaseController {
	@Resource
	private ITemplateService templateService;

	@RequestMapping("/templates.do")
	public String contact(HttpServletRequest request, Model model) {

		Pageable page = new PageRequest(0, 10, new Sort(Direction.ASC, "order"));
		Page<Template> result = templateService.findPage(page, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));
		List<Template> list = result.getContent();
		model.addAttribute("templates", list);

		return "site/templates.jsp";
	}
	
}
