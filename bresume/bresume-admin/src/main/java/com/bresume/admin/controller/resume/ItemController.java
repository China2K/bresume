package com.bresume.admin.controller.resume;

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
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.ResumeItemDto;
import com.bresume.core.model.entity.resume.ResumeItem;
import com.bresume.core.service.resume.IResumeItemService;
import com.bresume.core.service.resume.IResumeService;

@RequestMapping("/resume/item")
@Controller
public class ItemController extends AdminController {
	@Resource
	private IResumeService resumeService;

	@Resource
	private IResumeItemService resumeItemService;

	@RequestMapping("/setDefault.do")
	public @ResponseBody JSONObject setReommend(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "isDefault", required = true) boolean isDefault) {
		ResumeItem resumeItem = resumeItemService.findOne(id);
		if (resumeItem != null) {
			resumeItem.setIsDefault(isDefault);
			resumeItemService.update(resumeItem);
		}
		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping("/setRequired.do")
	public @ResponseBody JSONObject setRequired(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "required", required = true) boolean required) {
		ResumeItem resumeItem = resumeItemService.findOne(id);
		if (resumeItem != null) {
			resumeItem.setRequired(required);
			resumeItemService.update(resumeItem);
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
		Pageable pageable = new PageRequest(page - 1, pageSize, new Sort(
				Direction.ASC, "order"));
		Page<ResumeItemDto> list = resumeItemService.find(pageable,
				sbeans.toArray(new SearchBean[] {}));
		return this.toJSONResult(list.getTotalElements(), list.getContent(),
				pageSize, page);
	}

	@RequestMapping("/delete.do")
	public @ResponseBody JSONObject changStatus(HttpServletRequest request,
			@RequestParam(value = "ids", required = true) String ids,
			Model model) {
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			resumeItemService.delete(id);
		}

		return this.toJSONResult(true, "操作成功");
	}
	
	@RequestMapping("/form.do")
	public String form(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, Model model) {
		ResumeItem resumeItem = null;
		if (CommonUtils.isNotEmpty(id)) {
			resumeItem = resumeItemService.findOne(id);
		}
		if (resumeItem == null) {
			resumeItem = new ResumeItem();
		}
		model.addAttribute("resumeItem", resumeItem);
		return "/page/resume/item/form.jsp";
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public @ResponseBody JSONObject form(@ModelAttribute ResumeItem resumeItem,
			Model model) {
		if (CommonUtils.isNotEmpty(resumeItem.getId())) {

			ResumeItem uptResumeItem = resumeItemService.findOne(resumeItem
					.getId());
			uptResumeItem.setDesc(resumeItem.getDesc());
			uptResumeItem.setName(resumeItem.getName());
			uptResumeItem.setOrder(resumeItem.getOrder());
			uptResumeItem.setIsDefault(resumeItem.getIsDefault());
			uptResumeItem.setRequired(resumeItem.getRequired());
			uptResumeItem.setSn(resumeItem.getSn());
			resumeItemService.update(uptResumeItem);
		} else {
			resumeItem.setCreatedTime(new Date());
			resumeItemService.save(resumeItem);
		}

		return this.toJSONResult(true, "保存成功");
	}

}
