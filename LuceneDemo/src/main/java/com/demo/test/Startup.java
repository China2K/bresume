package com.demo.test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Startup
{
	private static final Logger logger = Logger.getLogger(Startup.class);
	
	private ApplicationContext context;

	public ApplicationContext getContext()
	{
		return context;
	}

	public void setContext(ApplicationContext context)
	{
		this.context = context;
	}
	
	public static void main(String[] args)
	{
		Startup startup = new Startup();
		String[] locations = new String[]{"applicationContext-*.xml"};
		ApplicationContext _context = new ClassPathXmlApplicationContext(locations);
		startup.setContext(_context);
		logger.info("加载Spring容器到BeanFactory...");
		System.out.println("11111111111111111111");
		List<Product> products =new ArrayList<Product>();
		 Random ran = new Random();
		for(int i=0;i<10000;i++){
			products.add(new Product(UUID.randomUUID().toString(), "test"+i+ran.nextInt(5000), null, "just test", "sn-"+i));
		}
		System.out.println(products.size());
		try {
			System.out.println("start build index "+new Date());
			IndexUtils.rebuildOrUpdateIndex(products, true);
			System.out.println("end build index"+new Date());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
