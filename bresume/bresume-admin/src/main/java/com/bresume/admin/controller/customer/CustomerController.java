package com.bresume.admin.controller.customer;

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

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.constant.enums.RegisterType;
import com.bresume.core.common.constant.enums.UserStatus;
import com.bresume.core.common.utils.CommonUtils;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.UserDto;
import com.bresume.core.model.entity.user.User;
import com.bresume.core.service.resume.IResumeService;
import com.bresume.core.service.resume.ITemplateService;
import com.bresume.core.service.user.IContactService;
import com.bresume.core.service.user.IUserService;

@RequestMapping("/user")
@Controller
public class CustomerController extends BaseController {
	@Resource
	private ITemplateService templateService;

	@Resource
	private IResumeService resumeService;

	@Resource
	private IContactService constactService;

	@Resource
	private IUserService userService;

	@RequestMapping("/customers.do")
	public String customer(HttpServletRequest request, Model model) {
		return "customer.jsp";
	}

	@RequestMapping("/customerList.do")
	public @ResponseBody JSONObject customerList(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize,
			Model model) {
		
		List<SearchBean> sbeans = convert2SearchBean(request);
		sbeans.add(new SearchBean("status", "4", "!="));
		Pageable pageable = new PageRequest(page - 1, pageSize, null);
		Page<UserDto> list = userService.find(pageable, sbeans.toArray(new SearchBean[] {}));
		return this.toJSONResult(list.getTotalElements(), list.getContent(),
				pageSize, page);
	}

	@RequestMapping("/changStatus.do")
	public @ResponseBody JSONObject changStatus(
			HttpServletRequest request,
			@RequestParam(value = "status", required = true) Integer status,
			@RequestParam(value = "ids", required = true) String ids,
			Model model) {
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			User user = userService.findOne(id);
			if (user != null) {
				user.setStatus(status);
				user.setUpdatedBy(getCurrentUserId());
				user.setUpdatedTime(new Date());
				userService.update(user);
			}
		}

		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping("/form.do")
	public String form(HttpServletRequest request,
			@RequestParam(value = "id", required = false) String id, Model model) {
		User user = null;
		if (CommonUtils.isNotEmpty(id)) {
			user = userService.findOne(id);
		}
		if (user == null) {
			user = new User();
		}
		model.addAttribute("user", user);
		return "/page/user/form.jsp";
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public @ResponseBody JSONObject form(@ModelAttribute User user, Model model) {
		if (CommonUtils.isNotEmpty(user.getId())) {
			// TODO update
			User uptUser = userService.findOne(user.getId());
		} else {
			user.setRegisterType(RegisterType.ADMIN_ADD.getType());
			user.setCreatedBy(getCurrentLoginUser().getId());
			user.setCreatedTime(new Date());
			user.setIsEmailVerified(false);
			user.setIsPhoneVerified(false);
			user.setStatus(UserStatus.INTITAL.getCode());
			userService.save(user);
		}

		return this.toJSONResult(true, "保存成功");
	}

	@RequestMapping(value = "/load.do", method = RequestMethod.GET)
	public String load(@RequestParam(value = "id", required = true) String id,
			Model model) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "/page/user/detail.jsp";
	}

}
