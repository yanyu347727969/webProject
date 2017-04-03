package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.UserService;

import entity.User;


public class TestUserService {
	
	@Test
	public void test01(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		UserService us = (UserService) spring.getBean("userService");
		us.addUser(new User(0,"lq","123","123@qq.com","123456"));
	}

	@Test
	public void test02(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		UserService us = (UserService) spring.getBean("userService");
		User user = us.findUserByUsernameAndUserPass("yy", "123");
		System.out.println(user);
		
	}	
	@Test
	public void test03(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		UserService us = (UserService) spring.getBean("userService");
		User user = us.findUserByUsernameAndUserPass("llx", "123456");
		if(user!=null){
			us.deleteUser(user);
		}
	}
	@Test
	public void test04(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		UserService us = (UserService) spring.getBean("userService");
		User user = us.findUserByUsernameAndUserPass("ww", "654321");
		System.out.println(user);
		user.setUserpass("123456");
		us.updateUser(user);
	}
	@Test
	public void test05(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		UserService us = (UserService) spring.getBean("userService");
		User user = us.findUserByUsername("yy");
		System.out.println(user);
	}
	@Test
	public void test06(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		UserService us = (UserService) spring.getBean("userService");
		User user = us.findUserByUsername("lq");
		user.setUserpass("123");
		us.updateUser(user);
		System.out.println(user);
	}
}
