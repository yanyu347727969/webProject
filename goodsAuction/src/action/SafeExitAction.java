package action;

import com.opensymphony.xwork2.ActionContext;



public class SafeExitAction {
	
	public String execute(){
		ActionContext.getContext().getSession().clear();
		return "success";
	}

}
