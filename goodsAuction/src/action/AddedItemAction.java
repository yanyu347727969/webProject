package action;

import java.util.Date;

import com.opensymphony.xwork2.ActionContext;

import entity.Item;
import entity.Kind;
import entity.State;
import entity.User;

import service.ItemService;
import service.KindService;
import service.StateService;

public class AddedItemAction {
	
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private KindService kindService;
	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	}
	private StateService stateService;
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	private Integer item_id;//物品id
	private String item_name;//物品名称
	private String item_desc;//物品描述
	private Date addtime;//起拍时间
	private Date endtime;//结束时间
	private double init_price;//起拍价格
	private int kind_id;//类别
	private String captcha;//验证码
	private String error;//错误信息
	private String address;//实际交易地址
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
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
	public double getInit_price() {
		return init_price;
	}
	public void setInit_price(double init_price) {
		this.init_price = init_price;
	}
	public int getKind_id() {
		return kind_id;
	}
	public void setKind_id(int kind_id) {
		this.kind_id = kind_id;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String execute(){
		//判断验证码输入是否正确
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)){
			error="验证码输入不正确";
			return "error";
		}
		//获得物品拥有用户对象
		User owner_user = (User) ActionContext.getContext().getSession().get("user");
		//获得物品类别对象
		Kind kind = kindService.findKindById(kind_id);
		//获得物品状态对象
		State state = null;
		long start = addtime.getTime();//起拍时间
		long now = new Date().getTime();//当前时间
		long end =endtime.getTime();//结束时间 
		//起拍时间<=当前时间<结束时间时,物品状态为"正在拍卖中"
		if(start<=now&&now<end){
			state = stateService.findStateByState_name("正在拍卖中");
		}
		//结束时间>起拍时间>当前时间,物品状态为"准备拍卖中"
		else if(end>start&&start>now){
			state = stateService.findStateByState_name("准备拍卖中");
		}
		//起拍时间<结束时间<=当前时间,物品状态为"已结束拍卖"
		else if(start<end&&end<=now){
			state = stateService.findStateByState_name("已结束拍卖");
		}
		//其它则为error
		else{
			error = "起拍时间或结束时间出错";
			return "error";
		}
		Item item = new Item(null, item_name, item_desc, addtime,
				endtime, init_price, null,init_price, owner_user, kind, null, state, null,address);
		item_id = itemService.addItem(item);
		return "success";
	}
	
}
