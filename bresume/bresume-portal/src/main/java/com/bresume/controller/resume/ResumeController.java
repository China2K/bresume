package com.bresume.controller.resume;

import java.lang.reflect.Field;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.PortalController;
import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.ResumeItem;
import com.bresume.core.model.entity.resume.ResumeItemRef;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.resume.IResumeItemRefService;
import com.bresume.core.service.resume.IResumeItemService;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;

@RequestMapping("/resume")
@Controller
public class ResumeController extends PortalController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IResumeItemService resumeItemService;

	@Resource
	private IResumeItemRefService resumeItemRefService;

	@RequestMapping("/changeResumeCover")
	public @ResponseBody JSONObject changeResumeCover(
			@RequestParam(value = "resumeId", required = true) String resumeId,
			@RequestParam(value = "newImgUrl", required = true) String newUrl) {
		Resume resume = resumeService.findOne(resumeId);
		resume.setCoverUrl(newUrl);
		resume.setUpdatedTime(new Date());
		resumeService.update(resume);
		return this.toJSONResult(true);
	}

	@RequestMapping("/delete")
	public @ResponseBody JSONObject changeResumeCover(
			@RequestParam(value = "id", required = true) String id) {
		Resume resume = resumeService.findOne(id);
		resume.setStatus(CommonStatus.DELETED.getCode());
		resume.setUpdatedTime(new Date());
		resumeService.update(resume);
		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping("/mine")
	public String mine(HttpServletRequest request, Model model) {
		LoginUser loginUser = getCurrentLoginUser();
		SearchBean sb1 = new SearchBean("user.id", loginUser.getId(), "=");
		SearchBean sb2 = new SearchBean("status",
				CommonStatus.DELETED.getCode() + "", "!=");
		List<Resume> resumes = resumeService.findAll(new Sort(Direction.ASC,
				"order"), sb1, sb2);
		model.addAttribute("resumes", resumes);
		return "site/myResume.jsp";
	}

	@RequestMapping("/startBulidResume")
	public String contact(HttpServletRequest request, Model model) {
		Pageable page = new PageRequest(0, 10, new Sort(Direction.ASC, "order"));
		Page<Template> result = templateService.findPage(page, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));
		List<Template> list = result.getContent();
		model.addAttribute("templates", list);

		return "site/templates.jsp";
	}

	@RequestMapping("/buildResume")
	public String bulidResume(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "template", required = false) String sn,
			Model model) {
		System.out.println(id);
		Resume resume = null;
		int step = 1;

		if (CommonUtils.isNotEmpty(id)) {
			resume = resumeService.findOne(id);
			sn = resume.getTemplateSn();
			resume.setScore(resumeService.getScore(resume));
			step = 3;
		} else {
			resume = new Resume();
		}

		Template template = templateService.findUniqueBy("sn", sn);

		/*
		 * List<ResumeItem> allResumeItems = (List<ResumeItem>)
		 * resumeItemService .findAll(new Sort(Direction.ASC, "order"));
		 */
		List<ResumeItem> defaultItems = resumeItemService.findDefaultItems(id);
		List<ResumeItem> extraItems = resumeItemService.findExtraItems(id);
		model.addAttribute("defaultItems", defaultItems);
		model.addAttribute("extraItems", extraItems);

		model.addAttribute("template", template);

		Pageable page = new PageRequest(0, 10, new Sort(Direction.ASC, "order"));
		Page<Template> result = templateService.findPage(page, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));
		List<Template> list = result.getContent();
		model.addAttribute("templates", list);

		model.addAttribute("step", step);
		model.addAttribute("resume", resume);
		return "site/resume.jsp";
	}

	@RequestMapping("/resumeItem")
	public String reusmeItem(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String resumeId,
			@RequestParam(value = "itemSn", required = true) String sn,
			Model model) {
		ResumeItemType resumeItem = ResumeItemType.fromSn(sn);
		List<?> objItems = null;
		model.addAttribute("resumeId", resumeId);
		if (CommonUtils.isNotEmpty(resumeId) && CommonUtils.isNotEmpty(sn)) {
			objItems = resumeItemService.findResumeItem(resumeItem, resumeId);
		}
		if (objItems != null && objItems.size() > 0) {
			model.addAttribute("items", objItems);
			if (objItems.size() == 1) {
				model.addAttribute(resumeItem.getName(), objItems.get(0));
			}

		} else {
			try {
				Class<?> clazz = resumeItem.getClazz();
				Object obj = clazz.newInstance();

				Field field;
				try {
					field = clazz.getDeclaredField("resume");
					field.setAccessible(true);

					Resume resume = new Resume();
					resume.setId(resumeId);
					field.set(obj, resume);
					field.setAccessible(false);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}

				model.addAttribute(resumeItem.getName(), obj);
				model.addAttribute("items", null);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();

			}
		}
		model.addAttribute("resumeId", resumeId);
		return "site/module/" + ResumeItemType.fromSn(sn).getPage();
	}

	@RequestMapping("/save")
	public @ResponseBody JSONObject save(HttpServletRequest request,
			@ModelAttribute Resume resume) {
		LoginUser loginUser = getCurrentLoginUser();
		boolean isNameExist = resumeService.isExist(resume.getId(),
				resume.getName());
		if (isNameExist) {
			return this.toJSONResult(false, "简历名称已存在");
		}
		if (resume != null && CommonUtils.isNotEmpty(resume.getId())) {
			Resume oldResume = resumeService.findOne(resume.getId());
			if (oldResume == null) {
				return this.toJSONResult(false, "简历未找到");
			}
			oldResume.setName(resume.getName());
			oldResume.setDesc(resume.getDesc());
			oldResume.setUpdatedTime(new Date());
			resumeService.update(oldResume);
		} else {
			long count = resumeService.count();
			resume.setCreatedTime(new Date());
			resume.setStatus(CommonStatus.INTITAL.getCode());
			resume.setOrder((int) (count + 1));
			resume.setRecommended(false);
			resume.setIsPublic(false);
			resume.setScore(50);
			if (CommonUtils.isEmpty(resume.getCoverUrl())) {
				resume.setCoverUrl(IConstants.DEFAULT_RESUME_COVERURL);
			}

			User user = new User();
			user.setId(loginUser.getId());
			resume.setUser(user);
			resumeService.save(resume);

			// 创建简历模快
			List<ResumeItem> items = resumeItemService.findAll(null,
					new SearchBean("isDefault", "true", "="));
			for (ResumeItem item : items) {
				ResumeItemRef ref = new ResumeItemRef();
				ref.setResume(resume);
				ref.setItemSn(item.getSn());
				resumeItemRefService.save(ref);
			}
		}

		return this.toJSONResult(true, "保存成功", resume.getId());
	}

	@RequestMapping("/removeItem")
	public @ResponseBody JSONObject removeItem(HttpServletRequest request,
			@RequestParam(value = "itemSn", required = true) String sn,
			@RequestParam(value = "resumeId", required = true) String resumeId) {
		LoginUser loginUser = getCurrentLoginUser();
		ResumeItemRef ref = resumeItemRefService
				.findByResumeAndSn(resumeId, sn);
		if (ref.getResume().getUser().getId()
				.equalsIgnoreCase(loginUser.getId())) {
			resumeItemRefService.delete(ref);
		}
		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping("/setPublicity")
	public @ResponseBody JSONObject setPublicity(HttpServletRequest request,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "flag", required = true) Integer flag) {
		Resume resume = resumeService.findOne(id);
		if (resume != null) {
			resume.setIsPublic(flag.intValue() == 1);
			resume.setUpdatedTime(new Date());
			resumeService.update(resume);
		}
		return this.toJSONResult(true, "保存成功");

	}

	@RequestMapping("/addItem")
	public @ResponseBody JSONObject addItem(HttpServletRequest request,
			@RequestParam(value = "itemSn", required = true) String sn,
			@RequestParam(value = "resumeId", required = true) String resumeId) {
		List<ResumeItemRef> list = resumeItemRefService.findAll(null,
				new SearchBean("resume.id", resumeId, "="), new SearchBean(
						"itemSn", sn, "="));
		if (list != null && list.size() > 0) {
			return this.toJSONResult(true, "保存成功");
		}
		Resume resume = resumeService.findOne(resumeId);
		ResumeItem item = resumeItemService.findUniqueBy("sn", sn);
		if (resume == null || item == null) {
			return this.toJSONResult(false, "保存失败");
		}
		ResumeItemRef ref = new ResumeItemRef();
		ref.setResume(resume);
		ref.setItemSn(item.getSn());
		resumeItemRefService.save(ref);
		return this.toJSONResult(true, "保存成功");
	}

	

	@RequestMapping(value = "/getScore")
	public @ResponseBody JSONObject getScore(
			@RequestParam(value = "id", required = true) String id) {
		Resume resume = resumeService.findOne(id);
		if (resume == null)
			return this.toJSONResult(false, "未找到");
		int proc =resumeService.getScore(resume);
		return this.toJSONResult(true, proc);
	}


}
