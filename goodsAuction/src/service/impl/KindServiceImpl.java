package service.impl;

import java.util.List;

import dao.BidDao;
import dao.ItemDao;
import dao.KindDao;
import dao.StateDao;
import dao.UserDao;
import entity.Kind;
import service.KindService;

public class KindServiceImpl implements KindService {
	private BidDao bidDao;
	private ItemDao itemDao;
	private KindDao kindDao;
	private StateDao stateDao;
	private UserDao userDao;

	public void setBidDao(BidDao bidDao) {
		this.bidDao = bidDao;
	}
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
	@Override
	public Integer addKind(Kind kind) {
		return kindDao.addKind(kind);
	}
	@Override
	public void deleteKind(Kind kind) {
		kindDao.deleteKind(kind);
	}
	@Override
	public void updateKind(Kind kind) {
		kindDao.updateKind(kind);
	}
	@Override
	public Kind findKindById(int kind_id) {
		return kindDao.findKindById(kind_id);
	}
	@Override
	public List<Kind> findAllKind() {
		return kindDao.findAllKind();
	}
	@Override
	public Kind findKindByKind_name(String kind_name) {
		return kindDao.findKindByKind_name(kind_name);
	}

}
