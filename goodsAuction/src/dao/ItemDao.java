package dao;

import java.util.Date;
import java.util.List;

import entity.Item;
import entity.State;

public interface ItemDao {
	
	//增加一个拍卖物品
	public Integer addItem(Item item);
	
	//删除拍卖物品
	public void deleteItemById(Item item);
	
	//修改拍卖物品的信息
	public void updateItem(Item item);
	
	//查询拍卖物品
	public Item findItemById(int item_id);
	
	//通过物品名查询物品
	public List<Item> findItemByItem_name(String item_name);
	
	//查询所有的物品
	public List<Item> findAllItem();
	
	//通过物品状态查询所有物品
	public List<Item> findItemByState_name(String state_name);
	
	//查询所有物品的数量
	public long findAllItemCount();
	
	//多条件查询,查询数量
	public long findItemsCount(String item_desc,String item_name, Integer kind_id,
			Integer owner_user_id, Integer state_id, Integer winner_user_id);
	
	//多条件查询,带分页
	public List<Item> findItems(String item_desc,String item_name, Integer kind_id,
			Integer owner_user_id, Integer state_id, Integer winner_user_id,int pageNo, 
			int pageSize);
	
	public List<Item> findOwnerAuctionItemsByUser_id(String item_desc, String item_name,
			Integer kind_id, Integer owner_user_id, Integer state_id,
			Integer winner_user_id,int pageNo, int pageSize,Integer user_id);
}
