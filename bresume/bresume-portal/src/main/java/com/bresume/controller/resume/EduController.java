package com.bresume.controller.resume;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.PortalController;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.item.EduExperience;
import com.bresume.core.service.resume.IEduService;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;

@RequestMapping("/edu")
@Controller
public class EduController extends PortalController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IEduService eduService;

	@RequestMapping("/save")
	public @ResponseBody
	JSONObject reusmeItem(HttpServletRequest request,
			@ModelAttribute EduExperience eduExperience) {
		if (eduExperience != null
				&& CommonUtils.isNotEmpty(eduExperience.getId())) {
			// TODO update
			EduExperience oldEdu = eduService.findOne(eduExperience.getId());
			oldEdu.setDegree(eduExperience.getDegree());
			oldEdu.setSchoolName(eduExperience.getSchoolName());
			oldEdu.setMajorName(eduExperience.getMajorName());
			oldEdu.setDesc(eduExperience.getDesc());
			oldEdu.setStartDate(eduExperience.getStartDate());
			oldEdu.setEndDate(eduExperience.getEndDate());
			oldEdu.setUpdatedTime(new Date());
			eduService.update(oldEdu);
		} else {
			eduExperience.setCreatedTime(new Date());
			eduService.save(eduExperience);
		}

		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping("/test")
	public String test(HttpServletRequest request, Model model) {
		EduExperience eduExperience = new EduExperience();

		Resume r = new Resume();
		r.setId("1");
		eduExperience.setResume(r);
		model.addAttribute("eduExperience", eduExperience);
		return "site/module/eduExperience.jsp";
	}

	@RequestMapping("/load")
	public String load(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "resumeId", required = false) String resumeId, 
			
			Model model) {
		EduExperience edu = null;
		if (CommonUtils.isNotEmpty(id)) {
			edu = eduService.findOne(id);
			if (edu != null) {
				model.addAttribute("eduExperience", edu);
			}
		}
		if (edu == null) {
			model.addAttribute("eduExperience", new EduExperience());
		}

		return "site/module/item/eduItem.jsp";
	}
	
	
	
	@RequestMapping("/delete")
	public @ResponseBody
	JSONObject delete(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id, 
			Model model) {
		eduService.delete(id);
		return this.toJSONResult(true, "删除成功");
	}

	/*
	 * @RequestMapping("/save") public void testsave(HttpServletRequest
	 * request,
	 * 
	 * @ModelAttribute EduExperience eduExperience) {
	 * eduExperience.getSchoolName(); }
	 */
}
