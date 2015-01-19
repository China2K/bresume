package com.bresume.controller;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.bresume.core.ws.HelloFacade;

public class Test {
	static final String url = "http://localhost:8081/ws/soap/hello";

	public static void main(String[] args) {
//		testJaxWsClient();
		testDynamicClient();
	}

	public static void testJaxWsClient () {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(url);
		factory.setServiceClass(HelloFacade.class);

		HelloFacade hellofacade = factory.create(HelloFacade.class);
		String result = hellofacade.sayHello("word");
		System.out.println("testJaxWsClient"+result);
	}

	public static void testDynamicClient() {
		JaxWsDynamicClientFactory  factory = JaxWsDynamicClientFactory .newInstance();
		Client client = factory.createClient("http://localhost:8081/ws/soap/hello?wsdl");

		try {
			Object[] results = client.invoke("sayHello", "world");
			System.out.println("DynamicClientFactory"+results[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
