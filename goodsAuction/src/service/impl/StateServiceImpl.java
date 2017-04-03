package service.impl;

import java.util.List;

import dao.BidDao;
import dao.ItemDao;
import dao.KindDao;
import dao.StateDao;
import dao.UserDao;
import entity.State;
import service.StateService;

public class StateServiceImpl implements StateService {
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
	public Integer addState(State state) {
		return stateDao.addState(state);
	}
	@Override
	public void deleteState(State state) {
		stateDao.deleteState(state);
	}
	@Override
	public void updateState(State state) {
		stateDao.updateState(state);
	}
	@Override
	public State findStateById(int state_id) {
		return stateDao.findStateById(state_id);
	}
	@Override
	public State findStateByState_name(String state_name) {
		return stateDao.findStateByState_name(state_name);
	}
	@Override
	public List<State> findAllState() {
		return stateDao.findAllState();
	}

}
