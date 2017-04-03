package service;

import java.util.List;

import entity.Bid;

public interface BidService {
	
	//添加竞标
	public Integer addBid(Bid bid);
	
	
	//删除竞标
	public void deleteBid(Bid bid);
	
	
	//更新竞标
	public void updateBid(Bid bid);
	
	
	//查询竞标
	public Bid findBidById(int bid_id);
	
	
	//查询所有竞标
	public List<Bid> findAllBid();


	//通过竞标的物品id查询竞标
	public List<Bid> findBidByItem_id(Integer item_id);
	
	
	//通过物品id查询参与该物品竞拍的用户数
	public long findUserCountByItem_id(Integer item_id);
	
	
	//通过竞标的物品id查询竞价最高的竞标
	public Bid findMax_priceBidByItem_id(Integer item_id);
	
	
	//通过竞标的物品id查询竞价第二高的竞标
	public Bid findSecond_max_priceBidByItem_id(Integer item_id);

}
