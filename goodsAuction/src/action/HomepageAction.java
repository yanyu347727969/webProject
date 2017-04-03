package action;

import java.util.List;

import service.KindService;
import service.StateService;
import entity.Kind;
import entity.State;
/**
 * ��ҳ��action
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
	private List<Kind> kinds;//��Ʒ����������
	private List<State> states;//��Ʒ����״̬
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
		//��ѯ����״̬
		states = stateService.findAllState();
		//��ѯ�������
		kinds = kindService.findAllKind();
		
		return "success";
	}

}
