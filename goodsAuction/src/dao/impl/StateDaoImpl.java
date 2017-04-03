package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.StateDao;
import entity.State;

public class StateDaoImpl implements StateDao{
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Integer addState(State state) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer)session.save(state);
	}
	@Override
	public void deleteState(State state) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(state);
	}
	@Override
	public void updateState(State state) {
		Session session = sessionFactory.getCurrentSession();
		session.update(state);
	}
	@Override
	public State findStateById(int state_id) {
		Session session = sessionFactory.getCurrentSession();
		State state = (State)session.get(State.class, state_id);
		return state;
	}
	@Override
	public State findStateByState_name(String state_name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from State where state_name='"+state_name+"'");
		return (State) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<State> findAllState() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from State ");
		return query.list();
	}

}
