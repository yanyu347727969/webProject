package action.admin;

import entity.State;
import service.StateService;

/**
 * 用于管理员发布物品状态
 * @author Administrator
 *
 */
public class AddItemStateAction {
	private String state_name;
	private String captcha;
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	private StateService stateService;
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	
	public String execute(){
		//数据校验
		
		//将数据插入数据库
		State state = new State();
		state.setState_name(state_name);
		stateService.addState(state);
		
		return "success";
	}
}
