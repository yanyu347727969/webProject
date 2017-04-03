package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.BidDao;
import entity.Bid;

public class BidDaoImpl implements BidDao{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer addBid(Bid bid) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer)session.save(bid);
	}

	@Override
	public void deleteBid(Bid bid) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(bid);
	}

	@Override
	public void updateBid(Bid bid) {
		Session session = sessionFactory.getCurrentSession();
		session.update(bid);
	}

	@Override
	public Bid findBidById(int bid_id) {
		Session session = sessionFactory.getCurrentSession();
		Bid bid = (Bid)session.get(Bid.class, bid_id);
		return bid;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findAllBid() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Bid");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findBidByItem_id(Integer item_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Bid b where b.item.item_id='"+item_id+"'");
		return query.list();
	}

	@Override
	public long findUserCountByItem_id(Integer item_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(distinct b.user.user_id) from Bid b where b.item.item_id='"+item_id+"'");
		return (Long) query.uniqueResult();
	}

	@Override
	public Bid findMax_priceBidByItem_id(Integer item_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Bid b where b.bid_price=(select max(b.bid_price) from b where b.item.item_id='"+item_id+"') and item='"+item_id+"'");
		return (Bid) query.uniqueResult();
	}

	@Override
	public Bid findSecond_max_priceBidByItem_id(Integer item_id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Bid b where b.item.item_id='"+item_id+"' and b.bid_price=(select max(b.bid_price)from b where b.bid_price!=(select max(b.bid_price) from b))");
		return (Bid) query.uniqueResult();
	}

	@Override
	public List<Bid> findBidByUser_id(Integer user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
