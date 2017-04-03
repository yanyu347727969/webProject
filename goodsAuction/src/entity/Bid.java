package entity;

import java.io.Serializable;
import java.util.Date;

/*
 * 
 * ����ʵ����
 * 
 */
@SuppressWarnings("serial")
public class Bid implements Serializable {

	private Integer  bid_id;//������
	private Double bid_price;//����۸�
	private Date bid_date;//��������
	private Item item;//������Ʒ
	private User user;//������
	
	public Bid() {
		super();
	}
	public Bid(Integer bid_id, Double bid_price, Date bid_date, Item item,
			User user) {
		super();
		this.bid_id = bid_id;
		this.bid_price = bid_price;
		this.bid_date = bid_date;
		this.item = item;
		this.user = user;
	}

	public Integer getBid_id() {
		return bid_id;
	}

	public void setBid_id(Integer bid_id) {
		this.bid_id = bid_id;
	}

	public Double getBid_price() {
		return bid_price;
	}

	public void setBid_price(Double bid_price) {
		this.bid_price = bid_price;
	}

	public Date getBid_date() {
		return bid_date;
	}

	public void setBid_date(Date bid_date) {
		this.bid_date = bid_date;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Bid [bid_id=" + bid_id + ", bid_price=" + bid_price
				+ ", bid_date=" + bid_date + ", item=" + item + ", user="
				+ user + "]";
	}
}
