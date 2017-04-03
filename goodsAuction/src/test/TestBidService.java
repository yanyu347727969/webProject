package test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Bid;
import entity.Item;
import entity.User;

import service.BidService;
import service.ItemService;
import service.UserService;

public class TestBidService {
	@Test
	public void test01(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		ItemService is = (ItemService) spring.getBean("itemService");
		UserService us = (UserService) spring.getBean("userService");
		Item item = is.findItemById(42);
		User user = us.findUserById(21);
		bs.addBid(new Bid(null, 250.0, new Date(), item, user));
	}
	@Test
	public void test02(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		Bid bid = bs.findBidById(1);
		System.out.println(bid);
	}
	@Test
	public void test03(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		Bid bid = bs.findBidById(1);
		bs.deleteBid(bid);
	}
	@Test
	public void test04(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		List<Bid> list = bs.findAllBid();
		for (Bid bid : list) {
			System.out.println(bid);
		}
	}
	@Test
	public void test05(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		List<Bid> list = bs.findBidByItem_id(121);
		for (Bid bid : list) {
			System.out.println(bid);
		}
	}
	@Test
	public void test06(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		long countByItem_id = bs.findUserCountByItem_id(121);
		System.out.println(countByItem_id);
	}
	@Test
	public void test07(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		Bid bid = bs.findMax_priceBidByItem_id(43);
		System.out.println(bid);
	}
	@Test
	public void test08(){
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		BidService bs = (BidService) spring.getBean("bidService");
		Bid bid = bs.findSecond_max_priceBidByItem_id(42);
		System.out.println(bid);
	}
}
