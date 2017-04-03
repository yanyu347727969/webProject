package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.State;

import service.StateService;

public class TestStateService {
	
	@Test
	public void test01(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		StateService us = (StateService) spring.getBean("stateService");
		us.addState(new State(0, "��������ʱ��"));
	}
	@Test
	public void test02(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		StateService us = (StateService) spring.getBean("stateService");
		State state = us.findStateById(2);
		System.out.println(state);
		us.deleteState(state);
	}
	@Test
	public void test03(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		StateService us = (StateService) spring.getBean("stateService");
		State state = us.findStateById(4);
		System.out.println(state);
		state.setState_name("�ѽ�������");
		us.updateState(state);
	}
	@Test
	public void test04(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		StateService us = (StateService) spring.getBean("stateService");
		State state = us.findStateByState_name("������");
		System.out.println(state);
	}
	@Test
	public void test05(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		StateService us = (StateService) spring.getBean("stateService");
		List<State> list = us.findAllState();
		for (State state : list) {
			System.out.println(state);
		}
	}

}
