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
 * ͨ���ؼ���,��Ʒ״̬,��Ʒ�����ѯ������Ʒ��action
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
	private String keyword;//��ѯ�Ĺؼ���
	private Integer kind_id;//��Ʒ������id
	private Integer state_id;//��Ʒ��״̬id
	private int pageNo;//ҳ��
	private int pageSize;//ÿҳ�����������
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
		Map<Integer,Long> map = new HashMap<Integer, Long>();//<item_id,count>,��Ʒid�Ͳ������Ʒ���ĵ��û�����
		List<Item> items;
		//��ѯ��ǰ��ѯ�����µ�����item
		if(keyword.equals(""))
			keyword=null;
		items = itemService.findItems(keyword, keyword, kind_id, null, state_id, null, pageNo, pageSize);
		long count = itemService.findItemsCount(keyword, keyword, kind_id, null, state_id, null);//����������
		int pageCount = 0;//ҳ��
		//�����ܹ��ж���ҳ
		if(count%pageSize==0){//˵�����һҳ�������պ���pageSize
			pageCount = (int) (count/pageSize);
		}else{//˵�����һҳ��������pageSize
			pageCount = (int) (count/pageSize)+1;
		}
		for (Item item : items) {
			//��ѯ��ǰ������Ʒ���ĵ��û���
			long userCount = bidService.findUserCountByItem_id(item.getItem_id());
			map.put(item.getItem_id(), userCount);
		}
		List<Object> items_map = new ArrayList<Object>();//map���ڴ��������Ҫ���͵�����
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
