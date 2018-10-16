package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.tedu.feign.EurekaServiceFeign;

@RestController
public class HelloController {
	
	@Autowired
	private EurekaServiceFeign feign;
	@RequestMapping("/hello/{name}")
	@HystrixCommand(fallbackMethod="fallBackHello")
	public String hello(@PathVariable String name){
		
		return feign.hello(name);//调用服务提供者
		
	}
	
	//失败时
	
	public String fallBackHello(String name){
		//设置一个失败时默认值
		return "tony";
	}
}
