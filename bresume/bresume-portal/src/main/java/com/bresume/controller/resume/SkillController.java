package com.bresume.controller.resume;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.item.JobIntension;
import com.bresume.core.model.entity.resume.item.Skill;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ISkillService;

@RequestMapping("/skill")
@Controller
public class SkillController extends BaseController {

	@Resource
	private ISkillService skillService;

	@Resource
	private IResumeService resumeService;

	@RequestMapping("/save")
	public @ResponseBody
	JSONObject save(HttpServletRequest request,SkillModel skillModel,String resumeId) {
		if(CommonUtils.isEmpty(resumeId)){
			return this.toJSONResult(false, "简历错误");
		}
		Resume resume=  resumeService.findOne(resumeId);
		if(CommonUtils.isEmpty(resume)){
			return this.toJSONResult(false, "简历错误");
		}
		List<Skill> items=skillModel.getItems();
		for(Skill item:items){
			String id=item.getId();
			if(item.getStatus()!=null&&item.getStatus().intValue()==CommonStatus.DELETED.getCode()){
				if(CommonUtils.isNotEmpty(id)){
					skillService.delete(id);
				}
			}else{
				if(CommonUtils.isNotEmpty(id)){
					//update
					Skill uptSkill=skillService.findOne(id);
					uptSkill.setDesc(item.getDesc());
					uptSkill.setLevel(item.getLevel());
					uptSkill.setMasterTime(item.getMasterTime());
					uptSkill.setName(item.getName());
					uptSkill.setOrder(item.getOrder());
					uptSkill.setTimeUnitCode(item.getTimeUnitCode());
					uptSkill.setUpdatedTime(new Date());
					uptSkill.setResume(resume);
					skillService.update(uptSkill);
				}else{
					item.setCreatedTime(new Date());
					item.setResume(resume);
					skillService.save(item);
				}
			}
		}
		
		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping("/load")
	public String load(
			HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "resumeId", required = false) String resumeId,
			Model model) {
		return "site/module/jobIntension.jsp";
	}

	@RequestMapping("/delete")
	public @ResponseBody
	JSONObject delete(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id, Model model) {
		skillService.delete(id);
		return this.toJSONResult(true, "删除成功");
	}

}
