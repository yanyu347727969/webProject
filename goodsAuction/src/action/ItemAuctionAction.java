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
 * �û����뾺�ĺ�Ĳ���
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
		Bid max_priceBid = bidService.findMax_priceBidByItem_id(item_id);//�õ����ļ۸���ߵľ���
		
		//�����Ʒ�Ƿ���"��������"��״̬
		long endTime = item.getEndtime().getTime();
		Date now = new Date();
		if(item.getState().getState_name().equals("�ѽ�������")){
			error="����Ʒ�ѽ�������!";
			return "error";
		}
		if(now.getTime()>=endTime){
			if(!item.getState().getState_name().equals("�ѽ�������")){
				item.setState(stateService.findStateByState_name("�ѽ�������"));
				itemService.updateItem(item);
				error="����Ʒ�ѽ�������!";
				return "error";
			}
		}
		//У��bid_price�Ƿ���Ч
		if(item.getInit_price()>bid_price){
			error = "���ļ۸�С����Ʒ��ʼ�۸�";
			return "error";
		}
		if(max_priceBid!=null){
			if(bid_price<=max_priceBid.getBid_price()){
				error = "���ļ۸񲻸�����߾��ļ۸�";
				return "error";
			}
		}
		
		item.setMax_price(bid_price);//�������۸�
		if(max_priceBid!=null){
			if(max_priceBid.getBid_price()!=null){//��ǰ��Ʒ����߾��ļ۸�Ϊnullʱ,���ý��׼۸�Ϊ��ǰ��Ʒ����߼۸�
				item.setSecond_max_price(max_priceBid.getBid_price());
			}
			if(max_priceBid.getBid_price()==null){//��ǰ��Ʒ����߾��ļ۸�Ϊnullʱ,���ý��׼۸�Ϊ��ǰ��Ʒ�ĳ�ʼ�۸�
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
