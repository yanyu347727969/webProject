package service;

import entity.User;

public interface UserService {
	
	//增加用户
	public Integer addUser(User user);
	
	//删除用户
	public void deleteUser(User user);
	
	
	//更新用户
	public void updateUser(User user);
	
	
	//查询用户
	public User findUserByUsernameAndUserPass(String username,String userpass);
	
	
	//通过id查询用户
	public User findUserById(int user_id);
	
	
	//通过用户名查询用户
	public User findUserByUsername(String username);

}
