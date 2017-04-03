package entity;

import java.io.Serializable;
import java.util.Date;

/*
 * 
 * 物品实体类
 * 
 */
@SuppressWarnings("serial")
public class Item implements Serializable {

	private Integer item_id;//物品编号
	private String item_name;//物品名称
	private String item_desc;//物品描述
	private Date addtime;//起拍时间
	private Date endtime;//结束时间
	private Double init_price;//起拍价格
	private Double max_price;//最高价
	private Double second_max_price;//实际交易价格,将竞拍的第二高的价格作为实际的交易价格
	private User owner_user;//所有者
	private Kind kind;//物品类别
	private User winner_user;//赢取人
	private State state;//物品状态
	private String imagePath;//图片路径
	private String address;//竞拍完成后的线下交易地址
	
	public Item() {
		super();
	}

	public Item(Integer item_id, String item_name, String item_desc,
			Date addtime, Date endtime, Double init_price, Double max_price, 
			Double second_max_price, User owner_user, Kind kind, User winner_user, 
			State state, String imagePath,String address) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_desc = item_desc;
		this.addtime = addtime;
		this.endtime = endtime;
		this.init_price = init_price;
		this.max_price = max_price;
		this.second_max_price = second_max_price;
		this.owner_user = owner_user;
		this.kind = kind;
		this.winner_user = winner_user;
		this.state = state;
		this.imagePath = imagePath;
		this.address = address;
	}

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Double getInit_price() {
		return init_price;
	}

	public void setInit_price(Double init_price) {
		this.init_price = init_price;
	}

	public Double getMax_price() {
		return max_price;
	}

	public void setMax_price(Double max_price) {
		this.max_price = max_price;
	}

	public User getOwner_user() {
		return owner_user;
	}

	public void setOwner_user(User owner_user) {
		this.owner_user = owner_user;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public User getWinner_user() {
		return winner_user;
	}

	public void setWinner_user(User winner_user) {
		this.winner_user = winner_user;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Double getSecond_max_price() {
		return second_max_price;
	}
	public void setSecond_max_price(Double second_max_price) {
		this.second_max_price = second_max_price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name
				+ ", item_desc=" + item_desc + ", addtime=" + addtime
				+ ", endtime=" + endtime + ", init_price=" + init_price
				+ ", max_price=" + max_price + ", second_max_price="
				+ second_max_price + ", owner_user=" + owner_user + ", kind="
				+ kind + ", winner_user=" + winner_user + ", state=" + state
				+ ", imagePath=" + imagePath + ", address=" + address + "]";
	}
}
