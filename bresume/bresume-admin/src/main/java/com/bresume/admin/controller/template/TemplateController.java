package com.bresume.admin.controller.template;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.AdminController;
import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.TemplateDto;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;

@RequestMapping("/tem")
@Controller
public class TemplateController extends AdminController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@RequestMapping("/hot.do")
	public String index(HttpServletRequest request, Model model) {

		List<Template> hotTemplates = templateService.findHostTemplates(CommonStatus.ACTIVE.getCode());
		model.addAttribute("hotTemplates", hotTemplates);
		return "/page/site/hotTemplates.jsp";
	}

	@RequestMapping("/setReommends.do")
	public @ResponseBody JSONObject setReommends(HttpServletRequest request,
			@RequestParam(value = "ids", required = true) String ids,
			@RequestParam(value = "recommend", required = true) boolean recommend
			) {
		
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			Template template = templateService.findOne(id);
			if(template!=null){
				template.setRecommended(recommend);
				template.setUpdatedBy(getCurrentUserId());
				templateService.update(template);
			}
		}
		return this.toJSONResult(true, "操作成功");
	}
	
	@RequestMapping("/setReommend.do")
	public @ResponseBody JSONObject setReommend(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "recommend", required = true) boolean recommend,
			@RequestParam(value = "order", required = false ,defaultValue="0") int order
			) {
		Template template = templateService.findOne(id);
		if(template!=null){
			template.setRecommended(recommend);
			template.setUpdatedBy(getCurrentUserId());
			if(order!=0){
				template.setOrder(order);
			}
			templateService.update(template);
		}
		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping("/list.do")
	public @ResponseBody JSONObject customerList(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize,
			Model model) {

		List<SearchBean> sbeans = convert2SearchBean(request);
		sbeans.add(new SearchBean("status", "4", "!="));
		Pageable pageable = new PageRequest(page - 1, pageSize, new Sort(
				Direction.ASC, "order"));
		Page<TemplateDto> list = templateService.find(pageable,
				sbeans.toArray(new SearchBean[] {}));
		return this.toJSONResult(list.getTotalElements(), list.getContent(),
				pageSize, page);
	}
	
	@RequestMapping("/listAll.do")
	public @ResponseBody JSONObject listAll(
			HttpServletRequest request,
			Model model) {

		List<SearchBean> sbeans = convert2SearchBean(request);
		sbeans.add(new SearchBean("status", CommonStatus.ACTIVE.getCode()+"", "="));
		List<TemplateDto> list = templateService.find(sbeans.toArray(new SearchBean[] {}));
		return this.toJSONResult( list.size(),list);
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

			Template uptTemplate = templateService.findOne(template.getId());
			uptTemplate.setCoverUrl(template.getCoverUrl());
			uptTemplate.setDesc(template.getDesc());
			uptTemplate.setName(template.getName());
			uptTemplate.setOrder(template.getOrder());
			uptTemplate.setRecommended(template.getRecommended());
			uptTemplate.setSiteUrl(template.getSiteUrl());
			uptTemplate.setSource(template.getSource());
			uptTemplate.setType(template.getType());
			uptTemplate.setUpdatedBy(getCurrentUserId());
			uptTemplate.setUpdatedTime(new Date());
			templateService.update(uptTemplate);
		} else {
			template.setCreatedTime(new Date());
			template.setCreatedBy(getCurrentUserId());
			if (CommonUtils.isEmpty(template.getStatus())) {
				template.setStatus(CommonStatus.INTITAL.getCode());
			}
			template.setOrder(0);
			template.setCoverUrl(IConstants.DEFAULT_TEMPLATE_COVERURL);
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

	@RequestMapping(value = "/recommend.do")
	public @ResponseBody JSONObject recommoned(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "recommend", required = false) boolean recommend) {
		Template uptTemplate = templateService.findOne(id);
		if (uptTemplate != null) {
			uptTemplate.setRecommended(recommend);
			uptTemplate.setUpdatedTime(new Date());
			uptTemplate.setUpdatedBy(getCurrentUserId());
			templateService.update(uptTemplate);
		}

		return this.toJSONResult(true, "保存成功");
	}

}
