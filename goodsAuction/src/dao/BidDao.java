package dao;

import java.util.List;

import entity.Bid;

public interface BidDao {

	//����һ������
	public Integer addBid(Bid bid);
	
	//ɾ������
	public void  deleteBid(Bid bid);
	
	//���¾���
	public void updateBid(Bid bid);
	
	//��ѯ����
	public Bid findBidById(int bid_id);
	
	//��ѯ���еľ���
	public List<Bid> findAllBid();
	
	//ͨ���������Ʒid��ѯ����
	public List<Bid> findBidByItem_id(Integer item_id);
	
	//ͨ����Ʒid��ѯ�������Ʒ���ĵ��û���
	public long findUserCountByItem_id(Integer item_id);
	
	//ͨ���������Ʒid��ѯ������ߵľ���
	public Bid findMax_priceBidByItem_id(Integer item_id);
	
	//ͨ���������Ʒid��ѯ���۵ڶ��ߵľ���
	public Bid findSecond_max_priceBidByItem_id(Integer item_id);
	
	//��ѯ�û�����ľ���
	public List<Bid> findBidByUser_id(Integer user_id);
}
