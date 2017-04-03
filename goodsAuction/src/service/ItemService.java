package service;

import java.util.List;

import entity.Item;

public interface ItemService {

	//�����Ʒ
	public Integer addItem(Item item);
	
	
	//ɾ����Ʒ
	public void deleteItem(Item item);
	
	
	//������Ʒ
	public void updateItem(Item item);
	
	
	//��ѯ��Ʒ
	public Item findItemById(int item_id);
	
	
	//ͨ����Ʒ����ѯ��Ʒ
	public List<Item> findItemByitem_name(String item_name);
	
	
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
	
	//��������ѯ�û����������,����ҳ
	public List<Item> findOwnerAuctionItemsByUser_id(String item_desc, String item_name,
			Integer kind_id, Integer owner_user_id, Integer state_id,
			Integer winner_user_id,int pageNo, int pageSize,Integer user_id);
}
