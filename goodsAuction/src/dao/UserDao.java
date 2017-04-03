package dao;

import entity.User;

public interface UserDao {

	//����һ���û�
	public Integer addUser(User user);
	
	//ɾ���û�
	public void deleteUser(User user);
	
	//�����û���Ϣ
	public void updateUser(User user);
	
	//ͨ���û�id��ѯ�û�
	public User findUserById(int user_id);
	
	//ͨ���û����������ѯ�û�
	public User findUserByUsernameAndUserpass(String username,String userpass);
	
	//ͨ���û�����ѯ�û�
	public User findUserByUsername(String username);
}
