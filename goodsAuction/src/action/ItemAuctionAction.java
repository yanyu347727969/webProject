package action;

import java.util.Date;

import service.BidService;
import service.ItemService;
import service.StateService;
import com.opensymphony.xwork2.ActionContext;
import entity.Bid;
import entity.Item;
import entity.User;
/**
 * 用户参与竞拍后的操作
 * @author Administrator
 *
 */
public class ItemAuctionAction {
	private ItemService itemService;
	private BidService bidService;
	private StateService stateService;
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}
	private Integer item_id;
	private Double bid_price;
	private String error;
	public void setError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public Double getBid_price() {
		return bid_price;
	}
	public void setBid_price(Double bid_price) {
		this.bid_price = bid_price;
	}


	public String execute(){
		Item item = itemService.findItemById(item_id);
		Bid max_priceBid = bidService.findMax_priceBidByItem_id(item_id);//得到竞拍价格最高的竞拍
		
		//监测物品是否处于"正在拍卖"的状态
		long endTime = item.getEndtime().getTime();
		Date now = new Date();
		if(item.getState().getState_name().equals("已结束拍卖")){
			error="该物品已结束拍卖!";
			return "error";
		}
		if(now.getTime()>=endTime){
			if(!item.getState().getState_name().equals("已结束拍卖")){
				item.setState(stateService.findStateByState_name("已结束拍卖"));
				itemService.updateItem(item);
				error="该物品已结束拍卖!";
				return "error";
			}
		}
		//校验bid_price是否有效
		if(item.getInit_price()>bid_price){
			error = "竞拍价格小于物品初始价格";
			return "error";
		}
		if(max_priceBid!=null){
			if(bid_price<=max_priceBid.getBid_price()){
				error = "竞拍价格不高于最高竞拍价格";
				return "error";
			}
		}
		
		item.setMax_price(bid_price);//设置最大价格
		if(max_priceBid!=null){
			if(max_priceBid.getBid_price()!=null){//当前物品的最高竞拍价格不为null时,设置交易价格为当前物品的最高价格
				item.setSecond_max_price(max_priceBid.getBid_price());
			}
			if(max_priceBid.getBid_price()==null){//当前物品的最高竞拍价格为null时,设置交易价格为当前物品的初始价格
				item.setSecond_max_price(item.getInit_price());
			}
		}
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		Bid bid = new Bid();
		bid.setBid_date(now);
		bid.setBid_price(bid_price);
		bid.setItem(item);
		bid.setUser(user);
		bidService.addBid(bid);
		itemService.updateItem(item);
		
		return "success";
	}
}
