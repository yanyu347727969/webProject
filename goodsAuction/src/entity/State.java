package entity;

import java.io.Serializable;

/*
 * 
 * ��Ʒ״̬ʵ����
 * 
 */
@SuppressWarnings("serial")
public class State implements Serializable {

	private Integer state_id;//��Ʒ״̬���
	private String state_name;//��Ʒ״̬����
	
	public State() {
		super();
	}

	public State(Integer state_id, String state_name) {
		super();
		this.state_id = state_id;
		this.state_name = state_name;
	}

	public Integer getState_id() {
		return state_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	@Override
	public String toString() {
		return "State [state_id=" + state_id + ", state_name=" + state_name
				+ "]";
	}
}
