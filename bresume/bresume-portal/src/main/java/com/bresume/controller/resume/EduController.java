package com.bresume.controller.resume;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.item.EduExperience;
import com.bresume.core.service.resume.IEduService;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;

@RequestMapping("/edu")
@Controller
public class EduController extends BaseController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IEduService eduService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
		super.initBinder(request, binder);
	}

	@RequestMapping("/save.do")
	public @ResponseBody
	JSONObject reusmeItem(HttpServletRequest request,
			@ModelAttribute EduExperience eduExperience) {
		if (eduExperience != null
				&& CommonUtils.isNotEmpty(eduExperience.getId())) {
			// TODO update
			EduExperience oldEdu = eduService.findOne(eduExperience.getId());
		} else {
			// TODO add
			eduExperience.setCreatedTime(new Date());
			eduExperience.setResume(null);
			eduService.save(eduExperience);
		}

		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping("/test.do")
	public String test(HttpServletRequest request, Model model) {
		EduExperience eduExperience = new EduExperience();

		Resume r = new Resume();
		r.setId("1");
		eduExperience.setResume(r);
		model.addAttribute("eduExperience", eduExperience);
		return "site/item/eduExperience.jsp";
	}

	@RequestMapping("/load.do")
	public String load(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, Model model) {
		EduExperience edu=null;
		if(CommonUtils.isNotEmpty(id)){
			edu= eduService.findOne(id);
			if(edu!=null){
				model.addAttribute("eduExperience", edu);
			}
		}
		if(edu==null){
			model.addAttribute("eduExperience", new EduExperience() );
		}
		
		return "site/item/eduItem.jsp";
	}

	/*
	 * @RequestMapping("/save.do") public void testsave(HttpServletRequest
	 * request,
	 * 
	 * @ModelAttribute EduExperience eduExperience) {
	 * eduExperience.getSchoolName(); }
	 */
}
