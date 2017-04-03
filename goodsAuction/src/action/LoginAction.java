package action;

import service.UserService;

import com.opensymphony.xwork2.ActionContext;

import entity.User;


public class LoginAction {
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private String username;//�û���
	private String userpass;//����
	private String captcha;//��֤��
	private String error;//������Ϣ
	
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String login(){
		// ����û��username���� null  
		// ����username ���ı���û���κ�ֵ, ""
		if(username == null || username.equals("")) {
			error = "�û�������Ϊ��";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		if(userpass == null || userpass.equals("")) {
			error = "���벻��Ϊ��";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		// �ж���֤���Ƿ���ȷ...
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)) {
			error = "��֤�벻��ȷ";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		
		User user = userService.findUserByUsernameAndUserPass(username, userpass);
		System.out.println(user);
		if(user == null) {
			error = "�û������ڻ����벻��ȷ";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		
		// ��session�洢һ����¼���, ����user������Ϊ��¼���
		ActionContext.getContext().getSession().put("user", user);
		ActionContext.getContext().getSession().remove("error");//����֤�ɹ�ʱ,����תǰ���session�еĴ�����Ϣ
		// ��鶼ͨ����
		return "success";
	}
	
}
