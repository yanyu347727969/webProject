package dao;

import java.util.Date;
import java.util.List;

import entity.Item;
import entity.State;

public interface ItemDao {
	
	//����һ��������Ʒ
	public Integer addItem(Item item);
	
	//ɾ��������Ʒ
	public void deleteItemById(Item item);
	
	//�޸�������Ʒ����Ϣ
	public void updateItem(Item item);
	
	//��ѯ������Ʒ
	public Item findItemById(int item_id);
	
	//ͨ����Ʒ����ѯ��Ʒ
	public List<Item> findItemByItem_name(String item_name);
	
	//��ѯ���е���Ʒ
	public List<Item> findAllItem();
	
	//ͨ����Ʒ״̬��ѯ������Ʒ
	public List<Item> findItemByState_name(String state_name);
	
	//��ѯ������Ʒ������
	public long findAllItemCount();
	
	//��������ѯ,��ѯ����
	public long findItemsCount(String item_desc,String item_name, Integer kind_id,
			Integer owner_user_id, Integer state_id, Integer winner_user_id);
	
	//��������ѯ,����ҳ
	public List<Item> findItems(String item_desc,String item_name, Integer kind_id,
			Integer owner_user_id, Integer state_id, Integer winner_user_id,int pageNo, 
			int pageSize);
	
	public List<Item> findOwnerAuctionItemsByUser_id(String item_desc, String item_name,
			Integer kind_id, Integer owner_user_id, Integer state_id,
			Integer winner_user_id,int pageNo, int pageSize,Integer user_id);
}
