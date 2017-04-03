package dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import dao.ItemDao;
import entity.Bid;
import entity.Item;

public class ItemDaoImpl implements ItemDao{
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer addItem(Item item) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer)session.save(item);
	}

	@Override
	public void deleteItemById(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(item);
	}

	@Override
	public void updateItem(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.update(item);
	}

	@Override
	public Item findItemById(int item_id) {
		Session session = sessionFactory.getCurrentSession();
		Item item = (Item)session.get(Item.class, item_id);
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findItemByItem_name(String item_name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Item where item_name='"+item_name+"'");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAllItem() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Item");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findItems(String item_desc,String item_name, Integer kind_id,
			Integer owner_user_id, Integer state_id, Integer winner_user_id,int pageNo, 
			int pageSize) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Item.class);
		//这里是将物品的描述和名称用or连接起来,搜素的关键字无论是出现在名称中还是出现在描述中都可以查出来
		if(item_desc!=null&&!item_desc.equals("")&&item_name!=null&&!item_name.equals("")){
			c.add(Restrictions.or(Restrictions.ilike("item_desc", "%"+item_desc+"%"), Restrictions.ilike("item_name", "%"+item_name+"%")));
		}
		//通过物品类别查询
		if(kind_id!=null&&!kind_id.equals("")){
			c.add(Restrictions.eq("kind.kind_id", kind_id));
		}
		//通过物品所有者查询
		if(owner_user_id!=null&&!owner_user_id.equals("")){
			c.add(Restrictions.eq("owner_user.user_id", owner_user_id));
		}
		//通过物品状态查询
		if(state_id!=null&&!state_id.equals("")){
			c.add(Restrictions.eq("state.state_id", state_id));
		}
		//通过物品最终得主查询
		if(winner_user_id!=null&&!winner_user_id.equals("")){
			c.add(Restrictions.eq("winner_user.user_id", winner_user_id));
		}
		c.addOrder(Order.asc("item_id"));
		c.setFirstResult((pageNo-1)*pageSize);
		c.setMaxResults(pageSize);
		
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findItemByState_name(String state_name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Item i where i.state.state_name='"+state_name+"'");
		return query.list();
	}

	@Override
	public long findAllItemCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Item");
		return (Long) query.uniqueResult();
	}

	@Override
	public long findItemsCount(String item_desc, String item_name,
			Integer kind_id, Integer owner_user_id, Integer state_id,
			Integer winner_user_id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Item.class);
		//这里是将物品的描述和名称用or连接起来,搜素的关键字无论是出现在名称中还是出现在描述中都可以查出来
		if(item_desc!=null&&!item_desc.equals("")&&item_name!=null&&!item_name.equals("")){
			c.add(Restrictions.or(Restrictions.ilike("item_desc", "%"+item_desc+"%"), Restrictions.ilike("item_name", "%"+item_name+"%")));
		}
		//通过物品类别查询
		if(kind_id!=null&&!kind_id.equals("")){
			c.add(Restrictions.eq("kind.kind_id", kind_id));
		}
		//通过物品所有者查询
		if(owner_user_id!=null&&!owner_user_id.equals("")){
			c.add(Restrictions.eq("owner_user.user_id", owner_user_id));
		}
		//通过物品状态查询
		if(state_id!=null&&!state_id.equals("")){
			c.add(Restrictions.eq("state.state_id", state_id));
		}
		//通过物品最终得主查询
		if(winner_user_id!=null&&!winner_user_id.equals("")){
			c.add(Restrictions.eq("winner_user.user_id", winner_user_id));
		}
		c.setProjection(Projections.rowCount());
		return (Long) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findOwnerAuctionItemsByUser_id(String item_desc,
			String item_name, Integer kind_id, Integer owner_user_id,
			Integer state_id, Integer winner_user_id, int pageNo, int pageSize,
			Integer user_id) {
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Bid.class, "Bid")
			    .add(Restrictions.eq("user.user_id", user_id));
		DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(Item.class);
		detachedCriteria2.add(Subqueries.propertyIn("item_id", detachedCriteria));
		Criteria c = session.createCriteria(Item.class,"Item");
		//Criteria c = session.createCriteria(Item.class);
		//这里是将物品的描述和名称用or连接起来,搜素的关键字无论是出现在名称中还是出现在描述中都可以查出来
		if(item_desc!=null&&!item_desc.equals("")&&item_name!=null&&!item_name.equals("")){
			c.add(Restrictions.or(Restrictions.ilike("item_desc", "%"+item_desc+"%"), Restrictions.ilike("item_name", "%"+item_name+"%")));
		}
		//通过物品类别查询
		if(kind_id!=null&&!kind_id.equals("")){
			c.add(Restrictions.eq("kind.kind_id", kind_id));
		}
		//通过物品所有者查询
		if(owner_user_id!=null&&!owner_user_id.equals("")){
			c.add(Restrictions.eq("owner_user.user_id", owner_user_id));
		}
		//通过物品状态查询
		if(state_id!=null&&!state_id.equals("")){
			c.add(Restrictions.eq("state.state_id", state_id));
		}
		//通过物品最终得主查询
		if(winner_user_id!=null&&!winner_user_id.equals("")){
			c.add(Restrictions.eq("winner_user.user_id", winner_user_id));
		}
		c.addOrder(Order.asc("item_id"));
		c.setFirstResult((pageNo-1)*pageSize);
		c.setMaxResults(pageSize);
		
		c.add(Subqueries.propertyIn("item_id", detachedCriteria2));
		return c.list();
	}
}
