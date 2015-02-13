package com.bresume.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.PortalController;
import com.bresume.core.common.base.sys.SessionContextHolder;
import com.bresume.core.common.constant.IConstants;
import com.bresume.core.common.constant.IPortalConstants;
import com.bresume.core.common.constant.enums.CommonStatus;
import com.bresume.core.common.constant.enums.ContactStatus;
import com.bresume.core.common.constant.enums.ResumeItemType;
import com.bresume.core.common.exception.CoreException;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.common.utils.security.Encrypt;
import com.bresume.core.model.dto.LoginUser;
import com.bresume.core.model.dto.ResumeDto;
import com.bresume.core.model.dto.item.EduExperienceDto;
import com.bresume.core.model.entity.resume.Resume;
import com.bresume.core.model.entity.resume.ResumeItemRef;
import com.bresume.core.model.entity.resume.Template;
import com.bresume.core.model.entity.user.Contact;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.resume.IResumeItemRefService;
import com.bresume.core.service.resume.IResumeItemService;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;
import com.bresume.core.service.user.IContactService;
import com.bresume.core.service.user.IUserService;

@RequestMapping("/")
@Controller
public class IndexController extends PortalController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IContactService constactService;

	@Resource
	private IResumeItemService resumeItemService;

	@Resource
	private IResumeItemRefService resumeItemRefService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		List<Template> hotTemplates = templateService
				.findHostTemplates(CommonStatus.ACTIVE.getCode());

		List<ResumeDto> hotResumes = resumeService
				.findHostResumes(CommonStatus.ACTIVE.getCode());

		model.addAttribute("hotTemplates", hotTemplates);
		model.addAttribute("hotResumes", hotResumes);
		
//		setUserFromCookie(request);
		return "/site/index.jsp";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		return "/site/login.jsp";
	}

	@RequestMapping("/contact")
	public @ResponseBody JSONObject contact(
			HttpServletRequest request,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "cellPhone", required = false) String cellPhone,
			Model model) {

		Contact contact = new Contact();
		contact.setCellPhone(cellPhone);
		contact.setCreatedTime(new Date());
		contact.setName(name);
		contact.setMessage(message);
		contact.setStatus(ContactStatus.INTITAL.getCode());
		constactService.save(contact);
		return this.toJSONResult(true, "感谢您的宝贵意见,我会最快时间阅读并给您回复!");
	}

	@RequestMapping("/templates")
	public String templates(HttpServletRequest request, Model model) {

		Pageable page = new PageRequest(0, 10, new Sort(Direction.ASC, "order"));
		Page<Template> result = templateService.findPage(page, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="));
		List<Template> list = result.getContent();
		model.addAttribute("templates", list);

		return "site/templates.jsp";
	}

	@RequestMapping("/resumes")
	public String listResume(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			Model model) {

		Pageable pageable = new PageRequest(page, limit, new Sort(
				Direction.ASC, "order"));
		Page<ResumeDto> result = resumeService.find(pageable, new SearchBean(
				"status", CommonStatus.ACTIVE.getCode() + "", "="),
				new SearchBean("isPublic", "1", "="));

		model.addAttribute("resumes", result.getContent());

		return "site/resumes.jsp";
	}

	@RequestMapping(value = "/resumes/{resumeName}", method = RequestMethod.GET)
	public String view(@PathVariable String resumeName, Model model) {

		Resume resume = resumeService.findUniqueBy("name", resumeName);
		if (resume == null) {
			return "404";
		}
		int score = resumeService.getScore(resume);
		if (score < 51) {
			return "404";
		}
		int status = resume.getStatus();
		boolean isPub = resume.getIsPublic();
		// 逻辑判断，在非激活状态，或者非公开状态下，只有本人可以查看
		if ((status != CommonStatus.ACTIVE.getCode() || !isPub)
				&& (this.getCurrentUserId() == null || !this.getCurrentUserId()
						.equals(resume.getUser().getId()))) {
			return "404";
		}
		model.addAttribute("resume", resume);

		String resumeId = resume.getId();

		String templatesn = resume.getTemplateSn();
		if (CommonUtils.isEmpty(templatesn)) {
			templatesn = IConstants.DEFAULT_TEMPLATE;
		}

		// Template template = templateService.findUniqueBy("sn", templatesn);
		String page = "resume/resume-" + templatesn + ".jsp";

		List<ResumeItemRef> refs = resume.getRefs();
		for (ResumeItemRef ref : refs) {
			String sn = ref.getItemSn();
			ResumeItemType resumeItem = ResumeItemType.fromSn(sn);
			List<?> objItems = null;
			objItems = resumeItemService
					.findResumeItemDto(resumeItem, resumeId);
			int type = resumeItem.getType();
			if (type == 1) {
				model.addAttribute(resumeItem.getName() + "s", CommonUtils
						.isEmpty(objItems) ? new ArrayList() : objItems);

				if (sn.equals(ResumeItemType.EDU_EXPERIENCE.getSn())) {
					model.addAttribute(resumeItem.getName(),
							getMixDegree((List<EduExperienceDto>) objItems));
				}
			} else {
				if (CommonUtils.isNotEmpty(objItems)) {
					model.addAttribute(resumeItem.getName(), objItems.get(0));
				} else {
					Class<?> clazz = resumeItem.getDtoClazz();
					Object obj;
					try {
						obj = clazz.newInstance();
						model.addAttribute(resumeItem.getName(), obj);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}

				}
			}

		}

		return page;
	}

	@RequestMapping(value = "/resumes/{resumeName}/download")
	public void download(HttpServletRequest request, HttpServletResponse resp,
			@PathVariable String resumeName) {
		String url = "http://www.bresume.com/resumes/" + resumeName;

		URL u;
		InputStream is = null;
		OutputStream os = null;

		try {
			u = new URL(url);
			is = u.openStream();
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			// 清空response
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setCharacterEncoding("UTF-8");
			// 设置response的Header
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(resumeName + ".html", "UTF-8")); // 处理中文文件名
			resp.addHeader("Content-Length", "" + buffer.length);
			os = new BufferedOutputStream(resp.getOutputStream());
			os.write(buffer);
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private EduExperienceDto getMixDegree(List<EduExperienceDto> list) {
		if (CommonUtils.isEmpty(list)) {
			return new EduExperienceDto();
		}
		int max = 0;
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			EduExperienceDto dto = list.get(i);
			if (dto.getdCode() > max) {
				max = dto.getdCode();
				index = i;
			}
		}
		return list.get(index);
	}
	
}
