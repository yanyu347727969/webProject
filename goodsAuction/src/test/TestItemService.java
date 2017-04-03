package test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.ItemService;
import service.KindService;
import service.StateService;
import service.UserService;
import entity.Item;
import entity.Kind;
import entity.State;
import entity.User;

public class TestItemService {
	@Test
	public void test01(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		UserService us = (UserService) spring.getBean("userService");
		KindService ks = (KindService) spring.getBean("kindService");
		StateService ss = (StateService) spring.getBean("stateService");
		User user1 = us.findUserByUsername("yy");
		User user2 = us.findUserByUsername("lq");
		Kind kind = ks.findKindByKind_name("学习用品");
		State state = ss.findStateByState_name("准备拍卖中");
		Integer i = bs.addItem(new Item(null, "大一英语书", "8层型", new Date(), new Date(), 10.0,0.0, 0.0, user1, kind, 
				user2, state,"aaa","四川遂宁"));
		System.out.println(i);
	}
	@Test
	public void test02(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		
		List<Item> list = bs.findItemByitem_name("大一英语书");
		for (Item item : list) {
			System.out.println(item);
		}
	}
	@Test
	public void test03(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		UserService us = (UserService) spring.getBean("userService");
		KindService ks = (KindService) spring.getBean("kindService");
		StateService ss = (StateService) spring.getBean("stateService");
		User user1 = us.findUserByUsername("yy");
		User user2 = us.findUserByUsername("lq");
		Kind kind = ks.findKindByKind_name("学习用品");
		State state = ss.findStateByState_name("拍卖中");
		bs.updateItem(new Item(2, "大一英语书", "8层型", new Date(), new Date(), 10.0, 0.0,0.0, user1, kind, 
				user2, state,"bbb","四川遂宁"));
	}
	@Test
	public void test04(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		Item item = bs.findItemById(2);
		System.out.println(item);
	}
	@Test
	public void test05(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		List<Item> list = bs.findAllItem();
		for (Item item : list) {
			System.out.println(item);
		}
	}
	@Test
	public void test06(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		
		Item item = bs.findItemById(26);
		System.out.println(item);
		bs.deleteItem(item);
	}
	@Test
	public void test07(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		UserService us = (UserService) spring.getBean("userService");
		User user = us.findUserByUsername("lq");
		List<Item> items = bs.findItems(null, null, null, null, null, user.getUser_id(), 1, 10);
		System.out.println(items.size());
	}
	@Test
	public void test8(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		List<Item> items = bs.findItemByState_name("正在拍卖中");
		System.out.println(items.size());
		for (Item item : items) {
			System.out.println(item);
		}
	}
	@Test
	public void test9(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		Item item = bs.findItemById(3);
		UserService us = (UserService) spring.getBean("userService");
		User owner_user = us.findUserById(21);
		item.setOwner_user(owner_user);
		bs.updateItem(item);
		System.out.println("ok");
	}
	@Test
	public void test10(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		System.out.println(bs.findAllItemCount());
	}
	@Test
	public void test11(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ItemService bs = (ItemService) spring.getBean("itemService");
		System.out.println(bs.findItemsCount("篮球", null, null, null, null, null));
	}
	@Test
	public void test12(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		ItemService is = (ItemService) spring.getBean("itemService");
		List<Item> items = is.findOwnerAuctionItemsByUser_id(null, null, null, null, null, null, 1, 10, 21);
		for (Item item : items) {
			System.out.println(item);
		}
	}
}
