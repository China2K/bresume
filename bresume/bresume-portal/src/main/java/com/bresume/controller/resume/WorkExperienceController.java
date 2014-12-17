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

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.item.WorkExperience;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.IWorkExperienceService;

@RequestMapping("/we")
@Controller
public class WorkExperienceController extends BaseController {

	@Resource
	private IResumeService resumeService;

	@Resource
	private IWorkExperienceService workExperienceService;

	@RequestMapping("/save.do")
	public @ResponseBody
	JSONObject reusmeItem(HttpServletRequest request,
			@ModelAttribute WorkExperience workExperience) {
		if (workExperience != null
				&& CommonUtils.isNotEmpty(workExperience.getId())) {
			// TODO update
			WorkExperience uptWE = workExperienceService.findOne(workExperience.getId());
			uptWE.setCompanyName(workExperience.getCompanyName());
			uptWE.setDegree(workExperience.getDegree());
			uptWE.setDepartment(workExperience.getDepartment());
			uptWE.setDesc(workExperience.getDesc());
			uptWE.setEndDate(workExperience.getEndDate());
			uptWE.setTradeName(workExperience.getTradeName());
			uptWE.setPosition(workExperience.getPosition());
			uptWE.setStartDate(workExperience.getStartDate());
			uptWE.setUpdatedTime(new Date());
			workExperienceService.update(uptWE);
		} else {
			
			String resumeId = workExperience.getResume() != null ? workExperience
					.getResume().getId() : null;
			Resume resume = resumeService.findOne(resumeId);
			if (resume != null) {
				workExperience.setResume(resume);
				workExperience.setCreatedTime(new Date());
				workExperienceService.save(workExperience);
			} else {
				return this.toJSONResult(false, "保存失败");
			}
		}

		return this.toJSONResult(true, "保存成功",workExperience.getId());
	}


	@RequestMapping("/load.do")
	public String load(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "resumeId", required = false) String resumeId, 
			
			Model model) {
		WorkExperience pe = null;
		if (CommonUtils.isNotEmpty(id)) {
			pe = workExperienceService.findOne(id);
			if (pe != null) {
				model.addAttribute("workExperience", pe);
			}
		}
		if (pe == null) {
			model.addAttribute("workExperience", new WorkExperience());
		}

		return "site/module/item/weItem.jsp";
	}
	
	
	
	@RequestMapping("/delete.do")
	public @ResponseBody
	JSONObject delete(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id, 
			Model model) {
		workExperienceService.delete(id);
		return this.toJSONResult(true, "删除成功");
	}

}
