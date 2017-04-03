package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.KindDao;
import entity.Kind;

public class KindDaoImpl implements KindDao{
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer addKind(Kind kind) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer)session.save(kind);
	}

	@Override
	public void deleteKind(Kind kind) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(kind);
	}

	@Override
	public void updateKind(Kind kind) {
		Session session = sessionFactory.getCurrentSession();
		session.update(kind);
	}

	@Override
	public Kind findKindById(int kind_id) {
		Session session = sessionFactory.getCurrentSession();
		Kind kind = (Kind)session.get(Kind.class, kind_id);
		return kind;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kind> findAllKind() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Kind";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public Kind findKindByKind_name(String kind_name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Kind where kind_name='"+kind_name+"'";
		Query query = session.createQuery(hql);
		return (Kind) query.uniqueResult();
	}

}
