package com.bresume.admin.controller.resume;

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

import com.bresume.core.common.base.controller.PortalController;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.ResumeDto;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.service.resume.IResumeService;

@RequestMapping("/resume")
@Controller
public class ResumeController extends PortalController {

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
		Page<ResumeDto> list = resumeService.find(pageable,
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
			Resume resume = resumeService.findOne(id);
			if (resume != null) {
				resume.setStatus(status);
				resume.setUpdatedTime(new Date());
				resumeService.update(resume);
			}
		}

		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping("/form.do")
	public String form(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, Model model) {
		Resume resume = null;
		if (CommonUtils.isNotEmpty(id)) {
			resume = resumeService.findOne(id);
		}
		if (resume == null) {
			resume = new Resume();
		}
		model.addAttribute("resume", resume);
		return "/page/resume/form.jsp";
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public @ResponseBody JSONObject form(@ModelAttribute Resume resume,
			Model model) {
		if (CommonUtils.isNotEmpty(resume.getId())) {
			// TODO update
			Resume uptResume = resumeService.findOne(resume.getId());
		} else {
			resumeService.save(resume);
		}

		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping(value = "/load.do", method = RequestMethod.GET)
	public String load(@RequestParam(value = "id", required = true) String id,
			Model model) {
		Resume resume = resumeService.findOne(id);
		model.addAttribute("resume", resume);
		return "/page/resume/detail.jsp";
	}

	@RequestMapping(value = "/recommend.do")
	public @ResponseBody JSONObject recommoned(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "recommend", required = false) boolean recommend) {
		Resume uptResume = resumeService.findOne(id);
		if (uptResume != null) {
			uptResume.setRecommended(recommend);
			uptResume.setUpdatedTime(new Date());
			resumeService.update(uptResume);
		}

		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping("/hot.do")
	public String index(HttpServletRequest request, Model model) {

		List<ResumeDto> hotResumes = resumeService
				.findHostResumes(CommonStatus.ACTIVE.getCode());
		model.addAttribute("hotResumes", hotResumes);
		return "/page/site/hotResumes.jsp";
	}

	@RequestMapping("/setReommends.do")
	public @ResponseBody JSONObject setReommends(
			HttpServletRequest request,
			@RequestParam(value = "ids", required = true) String ids,
			@RequestParam(value = "recommend", required = true) boolean recommend) {

		String[] idArr = ids.split(",");
		for (String id : idArr) {
			Resume resume = resumeService.findOne(id);
			if (resume != null) {
				resume.setRecommended(recommend);
				resumeService.update(resume);
			}
		}
		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping("/setReommend.do")
	public @ResponseBody JSONObject setReommend(
			HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "recommend", required = true) boolean recommend,
			@RequestParam(value = "order", required = false, defaultValue = "0") int order) {
		Resume resume = resumeService.findOne(id);
		if (resume != null) {
			resume.setRecommended(recommend);
			if (order != 0) {
				resume.setOrder(order);
			}
			resumeService.update(resume);
		}
		return this.toJSONResult(true, "操作成功");
	}

}
