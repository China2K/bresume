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
import com.bresume.core.model.entity.resume.item.PersionalInfo;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.resume.IPersionalInfoService;
import com.bresume.core.service.resume.IResumeService;

@RequestMapping("/pi")
@Controller
public class PersionalInfoController extends BaseController {

	@Resource
	private IPersionalInfoService persionalInfoService;

	@Resource
	private IResumeService resumeService;

	@RequestMapping("/save")
	public @ResponseBody
	JSONObject save(HttpServletRequest request,
			@ModelAttribute PersionalInfo persionalInfo) {
		String id = persionalInfo.getId();
		if (CommonUtils.isNotEmpty(id)) {
			PersionalInfo uptPI = persionalInfoService.findOne(id);

			uptPI.setAddress(persionalInfo.getAddress());
			uptPI.setBirthday(persionalInfo.getBirthday());
			uptPI.setCellPhone(persionalInfo.getCellPhone());
			uptPI.setCredentialsNumber(persionalInfo.getCredentialsNumber());
			uptPI.setCredentialsType(persionalInfo.getCredentialsType());
			uptPI.setEmail(persionalInfo.getEmail());
			uptPI.setExperienceYear(persionalInfo.getExperienceYear());
			uptPI.setJobStatus(persionalInfo.getJobStatus());
			uptPI.setName(persionalInfo.getName());
			uptPI.setProfession(persionalInfo.getProfession());
			uptPI.setSalary(persionalInfo.getSalary());
			uptPI.setSalaryUnit(persionalInfo.getSalaryUnit());
			uptPI.setSex(persionalInfo.getSex());
			uptPI.setSiteUrl(persionalInfo.getSiteUrl());
			uptPI.setUpdatedBy(persionalInfo.getUpdatedBy());
			uptPI.setUpdatedTime(persionalInfo.getUpdatedTime());
			
			persionalInfoService.update(uptPI);
		} else {

			String resumeId = persionalInfo.getResume() != null ? persionalInfo
					.getResume().getId() : null;
			Resume resume = resumeService.findOne(resumeId);
			if (resume != null) {
				User loginUser=getCurrentLoginUser();
				persionalInfo.setResume(resume);
				persionalInfo.setCreatedTime(new Date());
				persionalInfo.setCreatedBy(loginUser.getUserName());
				persionalInfoService.save(persionalInfo);
			} else {
				return this.toJSONResult(false, "保存失败");
			}
		}

		return this.toJSONResult(true, "保存成功", persionalInfo.getId());
	}

	@RequestMapping("/load")
	public String load(
			HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "resumeId", required = false) String resumeId,
			Model model) {
		PersionalInfo pi = null;
		if (CommonUtils.isNotEmpty(id)) {
			pi = persionalInfoService.findOne(id);
			if (pi != null) {
				model.addAttribute("persionalInfo", pi);
			}
		}
		if (pi == null) {
			model.addAttribute("persionalInfo", new PersionalInfo());
		}

		return "site/module/persionalInfo.jsp";
	}

	@RequestMapping("/delete")
	public @ResponseBody
	JSONObject delete(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id, Model model) {
		persionalInfoService.delete(id);
		return this.toJSONResult(true, "删除成功");
	}

}
