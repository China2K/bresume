package com.bresume.admin.controller.template;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.TemplateDto;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;

@RequestMapping("/tem")
@Controller
public class TemplateController extends BaseController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@RequestMapping("/list.do")
	public @ResponseBody JSONObject customerList(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize,
			Model model) {

		List<SearchBean> sbeans = convert2SearchBean(request);
		sbeans.add(new SearchBean("status", "4", "!="));
		Pageable pageable = new PageRequest(page - 1, pageSize, null);
		Page<TemplateDto> list = templateService.find(pageable,
				sbeans.toArray(new SearchBean[] {}));
		return this.toJSONResult(list.getTotalElements(), list.getContent(),
				pageSize, page);
	}

	@RequestMapping("/changStatus.do")
	public @ResponseBody JSONObject changStatus(HttpServletRequest request,
			@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "ids", required = true) String ids,
			Model model) {
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			Template template = templateService.findOne(id);
			if (template != null) {
				template.setStatus(status);
				template.setUpdatedBy(getCurrentUserId());
				template.setUpdatedTime(new Date());
				templateService.update(template);
			}
		}

		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping("/form.do")
	public String form(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, Model model) {
		Template template = null;
		if (CommonUtils.isNotEmpty(id)) {
			template = templateService.findOne(id);
		}
		if (template == null) {
			template = new Template();
		}
		model.addAttribute("template", template);
		return "/page/template/form.jsp";
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public @ResponseBody JSONObject form(@ModelAttribute Template template,
			Model model) {
		if (CommonUtils.isNotEmpty(template.getId())) {
			// TODO update
			Template uptTemplate = templateService.findOne(template.getId());
		} else {
			templateService.save(template);
		}

		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping(value = "/load.do", method = RequestMethod.GET)
	public String load(@RequestParam(value = "id", required = true) String id,
			Model model) {
		Template template = templateService.findOne(id);
		model.addAttribute("template", template);
		return "/page/template/detail.jsp";
	}

}
