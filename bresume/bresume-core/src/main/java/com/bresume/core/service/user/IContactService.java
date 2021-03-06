/** 
 * Project Name:bresume-core 
 * File Name:IcontactService.java 
 * Package Name:com.bresume.core.service.user 
 * Date:2014-11-26下午10:13:51 
 * Copyright (c) 2014, bresume.com All Rights Reserved. 
 * 
*/  
  
package com.bresume.core.service.user;  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bresume.core.common.base.service.IGenericService;
import com.bresume.core.common.utils.search.SearchBean;
import com.bresume.core.model.dto.ContactDto;
import com.bresume.core.model.entity.user.Contact;

/** 
 * ClassName:IcontactService
 * Description:   TODO ADD REASON.
 * Date:     2014-11-26 下午10:13:51
 * @author   2k 
     
 */
public interface IContactService extends IGenericService<Contact,String> {
	
	Page<ContactDto> find(Pageable pageable, SearchBean... searchBeans) ;

}
  