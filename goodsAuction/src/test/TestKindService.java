package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Kind;

import service.KindService;

public class TestKindService {
	@Test
	public void test01(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		KindService ks = (KindService) spring.getBean("kindService");
		ks.addKind(new Kind(null,"生活用品","用作生活"));
	}
	
	@Test
	public void test02(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		KindService ks = (KindService) spring.getBean("kindService");
		List<Kind> list = ks.findAllKind();
		for (Kind kind : list) {
			System.out.println(kind);
		}
	}
	@Test
	public void test03(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		KindService ks = (KindService) spring.getBean("kindService");
		Kind kind = ks.findKindById(22);
		ks.deleteKind(kind);
	}
	@Test
	public void test04(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		KindService ks = (KindService) spring.getBean("kindService");
		Kind kind = ks.findKindById(2);
		System.out.println(kind);
		kind.setKind_desc("包括健身用品");
		ks.updateKind(kind);
		System.out.println(ks.findKindById(2));
	}
	@Test
	public void test05(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		KindService ks = (KindService) spring.getBean("kindService");
		Kind kind = ks.findKindByKind_name("运动用品");
		System.out.println(kind);
	}
}
