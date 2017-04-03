package service;

import java.util.List;

import entity.Item;

public interface ItemService {

	//添加物品
	public Integer addItem(Item item);
	
	
	//删除物品
	public void deleteItem(Item item);
	
	
	//更新物品
	public void updateItem(Item item);
	
	
	//查询物品
	public Item findItemById(int item_id);
	
	
	//通过物品名查询物品
	public List<Item> findItemByitem_name(String item_name);
	
	
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
	
	//多条件查询用户参与的拍卖,带分页
	public List<Item> findOwnerAuctionItemsByUser_id(String item_desc, String item_name,
			Integer kind_id, Integer owner_user_id, Integer state_id,
			Integer winner_user_id,int pageNo, int pageSize,Integer user_id);
}
