package com.bresume.admin.controller.customer;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bresume.core.common.base.controller.BaseController;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.ContactDto;
import com.bresume.core.model.entity.user.Contact;
import com.bresume.core.service.user.IContactService;

@RequestMapping("/advice")
@Controller
public class AdviceController extends BaseController {

	@Resource
	private IContactService contactService;

	@RequestMapping("/list.do")
	public @ResponseBody JSONObject customerList(
			HttpServletRequest request,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize,
			Model model) {
		
		List<SearchBean> sbeans = convert2SearchBean(request);
		sbeans.add(new SearchBean("status", "4", "!="));
		Pageable pageable = new PageRequest(page - 1, pageSize, new Sort(Direction.DESC, "createdTime"));
		Page<ContactDto> list = contactService.find(pageable, sbeans.toArray(new SearchBean[] {}));
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
			Contact contact = contactService.findOne(id);
			if (contact != null) {
				contact.setStatus(status);
				contactService.update(contact);
			}
		}

		return this.toJSONResult(true, "操作成功");
	}

	@RequestMapping(value = "/load.do", method = RequestMethod.GET)
	public String load(@RequestParam(value = "id", required = true) String id,
			Model model) {
		Contact contact = contactService.findOne(id);
		model.addAttribute("contact", contact);
		return "/page/contact/detail.jsp";
	}
	
	
	

}
