package filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.opensymphony.xwork2.ActionContext;

import entity.Bid;
import entity.Item;
import entity.State;
import entity.User;

import service.BidService;
import service.ItemService;
import service.StateService;
import service.UserService;

/**
 * 该过滤器的作用是当请求物品的数据的时候先明确物品的拍卖状态
 * @author Administrator
 *
 */
public class ItemStateUpdateFilter implements Filter {
	private StateService stateService;
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private BidService bidService;
	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("---------------进入拍卖物品信息校验过滤器---------------");
		long now = new Date().getTime();//当前系统时间
		State stateNow = stateService.findStateByState_name("正在拍卖中");
		State stateEnd = stateService.findStateByState_name("已结束拍卖");
		
		List<Item> items0 = itemService.findItemByState_name("准备拍卖中");//查询"准备拍卖中"的物品
		//处理"准备拍卖中"的物品
		for (Item item : items0) {
			//如果当前时间大于等于开始时间切小于等于结束时间,则说明物品正在拍卖 ,需要将物品状态修改为"正在拍卖中"
			if(item.getAddtime().getTime()<=now&&now<=item.getEndtime().getTime()){
				item.setState(stateNow);
				itemService.updateItem(item);
			}
			//如果当前时间大于或者等于结束时间,则说明物品已经结束拍卖,需要将物品状态修改为已结束拍卖
			//调用endAuction方法处理已经结束拍卖的物品
			if(now>=item.getEndtime().getTime()){
				endAuction(item,stateEnd);
			}
		}
		
		List<Item> items1 = itemService.findItemByState_name("正在拍卖中");//查询"正在拍卖中"的物品
		//处理"正在拍卖中"的物品"
		for (Item item : items1) {
			//如果当前时间大于或者等于结束时间,则说明物品已经结束拍卖,需要将物品状态修改为已结束拍卖
			if(now>=item.getEndtime().getTime()){
				endAuction(item,stateEnd);
			}				
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	/**
	 * 处理已经结束拍卖物品的方法
	 * @param items,需要处理的物品集合
	 * @param nowTime,当前时间
	 */
	private void endAuction(Item item,State stateEnd) {
		item.setState(stateEnd);//设置"已结束拍卖"
		//已经结束拍卖的物品需要设置赢取人
		//得到竞拍价格最大的那个竞拍bid
		List<Bid> bids = bidService.findBidByItem_id(item.getItem_id());
		if(!bids.isEmpty()){
			Bid bid = bids.get(0);
			for(int i=1 ; i < bids.size() ; i++){
				if(bids.get(i).getBid_price()>bid.getBid_price()){
					bid = bids.get(i);
				}
			}
			item.setWinner_user(bid.getUser());//设置竞拍赢取人
		}
		itemService.updateItem(item);
	}
}
