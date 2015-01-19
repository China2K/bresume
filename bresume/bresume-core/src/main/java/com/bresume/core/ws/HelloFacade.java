package com.bresume.core.ws;

import javax.jws.WebService;

@WebService
public interface HelloFacade {
	String sayHello(String msg);

}
