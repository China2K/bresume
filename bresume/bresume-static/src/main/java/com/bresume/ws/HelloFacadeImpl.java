package com.bresume.ws;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.bresume.core.ws.HelloFacade;

@WebService
@Component
public class HelloFacadeImpl implements HelloFacade{

	@Override
	public String sayHello(String msg) {
		System.out.println("msg="+msg);
		return msg+"_success";
	}

}
