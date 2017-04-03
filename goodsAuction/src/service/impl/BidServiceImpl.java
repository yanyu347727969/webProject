package service.impl;

import java.util.List;

import service.BidService;
import dao.BidDao;
import dao.ItemDao;
import dao.KindDao;
import dao.StateDao;
import dao.UserDao;
import entity.Bid;

public class BidServiceImpl implements BidService {
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
	public Integer addBid(Bid bid) {
		return bidDao.addBid(bid);
	}

	@Override
	public void deleteBid(Bid bid) {
		bidDao.deleteBid(bid);
	}

	@Override
	public void updateBid(Bid bid) {
		bidDao.updateBid(bid);
	}

	@Override
	public Bid findBidById(int bid_id) {
		return bidDao.findBidById(bid_id);
	}
	@Override
	public List<Bid> findAllBid() {
		return bidDao.findAllBid();
	}
	@Override
	public List<Bid> findBidByItem_id(Integer item_id) {
		return bidDao.findBidByItem_id(item_id);
	}
	@Override
	public long findUserCountByItem_id(Integer item_id) {
		return bidDao.findUserCountByItem_id(item_id);
	}
	@Override
	public Bid findMax_priceBidByItem_id(Integer item_id) {
		return bidDao.findMax_priceBidByItem_id(item_id);
	}
	@Override
	public Bid findSecond_max_priceBidByItem_id(Integer item_id) {
		return bidDao.findSecond_max_priceBidByItem_id(item_id);
	}

}
