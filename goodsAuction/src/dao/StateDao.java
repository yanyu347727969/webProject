package dao;

import java.util.List;

import entity.State;

public interface StateDao {
	
	//������Ʒ״̬
	public Integer addState(State state);
	
	//ɾ����Ʒ״̬
	public void deleteState(State state);
	
	//�޸���Ʒ״̬
	public void updateState(State state);

	//��ѯ��Ʒ״̬
	public State findStateById(int state_id);
	
	//ͨ����Ʒ״̬����ѯ��Ʒ״̬
	public State findStateByState_name(String state_name);
	
	//��ѯ���е�״̬
	public List<State> findAllState();
}
