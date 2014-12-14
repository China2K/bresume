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
import com.bresume.core.model.entity.resume.item.JobIntension;
import com.bresume.core.service.resume.IJobIntenService;
import com.bresume.core.service.resume.IResumeService;

@RequestMapping("/jobi")
@Controller
public class JobIntenController extends BaseController {

	@Resource
	private IJobIntenService jobIntenService;

	@Resource
	private IResumeService resumeService;

	@RequestMapping("/save.do")
	public @ResponseBody
	JSONObject save(HttpServletRequest request,
			@ModelAttribute JobIntension jobIntension) {
		String id = jobIntension.getId();
		if (CommonUtils.isNotEmpty(id)) {
			JobIntension uptJobInten = jobIntenService.findOne(id);

			uptJobInten.setAddress(jobIntension.getAddress());
			uptJobInten.setExpertSalary(jobIntension.getExpertSalary());
			uptJobInten.setJobType(jobIntension.getJobType());
			uptJobInten.setReadyTime(jobIntension.getReadyTime());
			uptJobInten.setSelfEvaluation(jobIntension.getSelfEvaluation());
			uptJobInten.setTrade(jobIntension.getTrade());
			uptJobInten.setUpdatedTime(new Date());
			jobIntenService.update(uptJobInten);
		} else {

			String resumeId = jobIntension.getResume() != null ? jobIntension
					.getResume().getId() : null;
			Resume resume = resumeService.findOne(resumeId);
			if (resume != null) {
				jobIntension.setResume(resume);
				jobIntension.setCreatedTime(new Date());
				jobIntenService.save(jobIntension);
			} else {
				return this.toJSONResult(false, "保存失败");
			}
		}

		return this.toJSONResult(true, "保存成功", jobIntension.getId());
	}

	@RequestMapping("/load.do")
	public String load(
			HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "resumeId", required = false) String resumeId,
			Model model) {
		JobIntension job = null;
		if (CommonUtils.isNotEmpty(id)) {
			job = jobIntenService.findOne(id);
			if (job != null) {
				model.addAttribute("jobExperience", job);
			}
		}
		if (job == null) {
			model.addAttribute("jobExperience", new JobIntension());
		}

		return "site/module/jobIntension.jsp";
	}

	@RequestMapping("/delete.do")
	public @ResponseBody
	JSONObject delete(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id, Model model) {
		jobIntenService.delete(id);
		return this.toJSONResult(true, "删除成功");
	}

}
