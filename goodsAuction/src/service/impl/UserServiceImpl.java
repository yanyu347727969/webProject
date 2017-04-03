package service.impl;

import java.util.List;

import dao.BidDao;
import dao.ItemDao;
import dao.KindDao;
import dao.StateDao;
import dao.UserDao;
import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService{
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
	public Integer addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}
	
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User findUserByUsernameAndUserPass(String username, String userpass) {
		return userDao.findUserByUsernameAndUserpass(username, userpass);
	}
	@Override
	public User findUserById(int user_id) {
		return userDao.findUserById(user_id);
	}
	@Override
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}
	
}
