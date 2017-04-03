package service;

import java.util.List;

import entity.State;

public interface StateService {
	
	//���״̬
	public Integer addState(State state);
	
	//ɾ��״̬
	public void deleteState(State state);
	
	//����״̬
	public void updateState(State state);
	
	//��ѯ״̬
	public State findStateById(int state_id);
	
	//ͨ��״̬����ѯ״̬
	public State findStateByState_name(String state_name);
	
	//��ѯ���е�״̬
	public List<State> findAllState();

}
