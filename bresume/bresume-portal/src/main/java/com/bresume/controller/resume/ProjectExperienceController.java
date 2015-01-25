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
import com.bresume.core.model.entity.resume.item.ProjectExperience;
import com.bresume.core.service.resume.IProjectExperienceService;
import com.bresume.core.service.resume.IResumeService;

@RequestMapping("/pe")
@Controller
public class ProjectExperienceController extends PortalController {

	@Resource
	private IResumeService resumeService;

	@Resource
	private IProjectExperienceService projectExperienceService;

	@RequestMapping("/save")
	public @ResponseBody
	JSONObject reusmeItem(HttpServletRequest request,
			@ModelAttribute ProjectExperience projectExperience) {
		if (projectExperience != null
				&& CommonUtils.isNotEmpty(projectExperience.getId())) {
			// TODO update
			ProjectExperience uptPE = projectExperienceService.findOne(projectExperience.getId());
			uptPE.setEndDate(projectExperience.getEndDate());
			uptPE.setProjectDesc(projectExperience.getProjectDesc());
			uptPE.setProjectName(projectExperience.getProjectName());
			uptPE.setRespDesc(projectExperience.getRespDesc());
			uptPE.setStartDate(projectExperience.getStartDate());
			uptPE.setUpdatedTime(new Date());
			projectExperienceService.update(uptPE);
		} else {
			
			String resumeId = projectExperience.getResume() != null ? projectExperience
					.getResume().getId() : null;
			Resume resume = resumeService.findOne(resumeId);
			if (resume != null) {
				projectExperience.setResume(resume);
				projectExperience.setCreatedTime(new Date());
				projectExperienceService.save(projectExperience);
			} else {
				return this.toJSONResult(false, "保存失败");
			}
		}

		return this.toJSONResult(true, "保存成功",projectExperience.getId());
	}


	@RequestMapping("/load")
	public String load(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "resumeId", required = false) String resumeId, 
			
			Model model) {
		ProjectExperience pe = null;
		if (CommonUtils.isNotEmpty(id)) {
			pe = projectExperienceService.findOne(id);
			if (pe != null) {
				model.addAttribute("projectExperience", pe);
			}
		}
		if (pe == null) {
			model.addAttribute("projectExperience", new ProjectExperience());
		}

		return "site/module/item/projectItem.jsp";
	}
	
	
	
	@RequestMapping("/delete")
	public @ResponseBody
	JSONObject delete(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id, 
			Model model) {
		projectExperienceService.delete(id);
		return this.toJSONResult(true, "删除成功");
	}

}
