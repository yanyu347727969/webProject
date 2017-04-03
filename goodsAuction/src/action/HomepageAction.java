package action;

import java.util.List;

import service.KindService;
import service.StateService;
import entity.Kind;
import entity.State;
/**
 * 主页的action
 * @author Administrator
 *
 */
public class HomepageAction {
	private StateService stateService;
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	private KindService kindService;
	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	}
	private List<Kind> kinds;//物品的所有种类
	private List<State> states;//物品所有状态
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public List<Kind> getKinds() {
		return kinds;
	}
	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public String execute(){
		//查询所有状态
		states = stateService.findAllState();
		//查询所有类别
		kinds = kindService.findAllKind();
		
		return "success";
	}

}
