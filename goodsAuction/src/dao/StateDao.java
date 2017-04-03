package dao;

import java.util.List;

import entity.State;

public interface StateDao {
	
	//增加物品状态
	public Integer addState(State state);
	
	//删除物品状态
	public void deleteState(State state);
	
	//修改物品状态
	public void updateState(State state);

	//查询物品状态
	public State findStateById(int state_id);
	
	//通过物品状态名查询物品状态
	public State findStateByState_name(String state_name);
	
	//查询所有的状态
	public List<State> findAllState();
}
