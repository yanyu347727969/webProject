package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.UserDao;
import entity.User;

public class UserDaoImpl implements UserDao{
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer)session.save(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public User findUserById(int user_id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, user_id);
		return user;
	}

	@Override
	public User findUserByUsernameAndUserpass(String username, String userpass) {
		Session session = sessionFactory.getCurrentSession();
		String str = "from User where username='"+username+"' and userpass='"+userpass+"'";
		Query query = session.createQuery(str);
		return (User)query.uniqueResult();
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public User findUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username='"+username+"'");
		return (User) query.uniqueResult();
	}

	

}
