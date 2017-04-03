package service.impl;

import java.util.List;

import service.ItemService;
import dao.BidDao;
import dao.ItemDao;
import dao.KindDao;
import dao.StateDao;
import dao.UserDao;
import entity.Item;

public class ItemServiceImpl implements ItemService{
	private BidDao bidDao;
	private ItemDao itemDao;
	private KindDao kindDao;
	private StateDao stateDao;
	private UserDao userDao;

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public void setKindDao(KindDao kindDao) {
		this.kindDao = kindDao;
	}
	public void setStateDao(StateDao stateDao) {
		this.stateDao = stateDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setBidDao(BidDao bidDao) {
		this.bidDao = bidDao;
	}
	
	@Override
	public Integer addItem(Item item) {
		return itemDao.addItem(item);
	}

	@Override
	public void deleteItem(Item item) {
		itemDao.deleteItemById(item);
	}

	@Override
	public void updateItem(Item item) {
		itemDao.updateItem(item);
	}

	@Override
	public Item findItemById(int item_id) {
		return itemDao.findItemById(item_id);
	}
	@Override
	public List<Item> findItemByitem_name(String item_name) {
		return itemDao.findItemByItem_name(item_name);
	}
	@Override
	public List<Item> findAllItem() {
		return itemDao.findAllItem();
	}
	@Override
	public List<Item> findItems(String item_desc,String item_name, Integer kind_id,
			Integer owner_user_id, Integer state_id, Integer winner_user_id,int pageNo, 
			int pageSize) {
		return itemDao.findItems(item_desc, item_name, kind_id, owner_user_id, state_id, winner_user_id, pageNo, pageSize);
	}
	@Override
	public List<Item> findItemByState_name(String state_name) {
		return itemDao.findItemByState_name(state_name);
	}
	@Override
	public long findAllItemCount() {
		return itemDao.findAllItemCount();
	}
	@Override
	public long findItemsCount(String item_desc, String item_name,
			Integer kind_id, Integer owner_user_id, Integer state_id,
			Integer winner_user_id) {
		return itemDao.findItemsCount(item_desc, item_name, kind_id, owner_user_id, state_id, winner_user_id);
	}
	@Override
	public List<Item> findOwnerAuctionItemsByUser_id(String item_desc,
			String item_name, Integer kind_id, Integer owner_user_id,
			Integer state_id, Integer winner_user_id, int pageNo, int pageSize,
			Integer user_id) {
		return itemDao.findOwnerAuctionItemsByUser_id(item_desc, item_name, kind_id, owner_user_id, state_id, winner_user_id, pageNo, pageSize, user_id);
	}

}
