package service;

import entity.User;

public interface UserService {
	
	//�����û�
	public Integer addUser(User user);
	
	//ɾ���û�
	public void deleteUser(User user);
	
	
	//�����û�
	public void updateUser(User user);
	
	
	//��ѯ�û�
	public User findUserByUsernameAndUserPass(String username,String userpass);
	
	
	//ͨ��id��ѯ�û�
	public User findUserById(int user_id);
	
	
	//ͨ���û�����ѯ�û�
	public User findUserByUsername(String username);

}
