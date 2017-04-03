package service;

import java.util.List;

import entity.State;

public interface StateService {
	
	//添加状态
	public Integer addState(State state);
	
	//删除状态
	public void deleteState(State state);
	
	//更新状态
	public void updateState(State state);
	
	//查询状态
	public State findStateById(int state_id);
	
	//通过状态名查询状态
	public State findStateByState_name(String state_name);
	
	//查询所有的状态
	public List<State> findAllState();

}
