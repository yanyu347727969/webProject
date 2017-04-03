package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.BidService;
import service.ItemService;
import entity.Item;
/**
 * 通过关键字,物品状态,物品种类查询拍卖物品的action
 * @author Administrator
 *
 */
public class SearchItemsAction {
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private BidService bidService;
	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}
	private String keyword;//查询的关键字
	private Integer kind_id;//物品的种类id
	private Integer state_id;//物品的状态id
	private int pageNo;//页数
	private int pageSize;//每页数据最大条数
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getKind_id() {
		return kind_id;
	}
	public void setKind_id(Integer kind_id) {
		this.kind_id = kind_id;
	}
	public Integer getState_id() {
		return state_id;
	}
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}
	
	public String execute(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		Map<Integer,Long> map = new HashMap<Integer, Long>();//<item_id,count>,物品id和参与该物品竞拍的用户数量
		List<Item> items;
		//查询当前查询条件下的所有item
		if(keyword.equals(""))
			keyword=null;
		items = itemService.findItems(keyword, keyword, kind_id, null, state_id, null, pageNo, pageSize);
		long count = itemService.findItemsCount(keyword, keyword, kind_id, null, state_id, null);//数据总条数
		int pageCount = 0;//页数
		//计算总共有多少页
		if(count%pageSize==0){//说明最后一页的数量刚好是pageSize
			pageCount = (int) (count/pageSize);
		}else{//说明最后一页数量少于pageSize
			pageCount = (int) (count/pageSize)+1;
		}
		for (Item item : items) {
			//查询当前参与物品竞拍的用户数
			long userCount = bidService.findUserCountByItem_id(item.getItem_id());
			map.put(item.getItem_id(), userCount);
		}
		List<Object> items_map = new ArrayList<Object>();//map用于存放所有需要传送的数据
		items_map.add(map);
		items_map.add(items);
		items_map.add(pageCount);
		try {
			Gson jeson = new Gson();
			String json = jeson.toJson(items_map);
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
