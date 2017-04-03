package dao;

import entity.User;

public interface UserDao {

	//增加一个用户
	public Integer addUser(User user);
	
	//删除用户
	public void deleteUser(User user);
	
	//更新用户信息
	public void updateUser(User user);
	
	//通过用户id查询用户
	public User findUserById(int user_id);
	
	//通过用户名和密码查询用户
	public User findUserByUsernameAndUserpass(String username,String userpass);
	
	//通过用户名查询用户
	public User findUserByUsername(String username);
}
