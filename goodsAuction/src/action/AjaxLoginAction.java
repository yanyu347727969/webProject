package action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

import entity.User;
import service.UserService;

/**
 * ʹ��ajax��¼��action
 * @author Administrator
 *
 */
public class AjaxLoginAction {
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
	
	public String execute(){
		// ����û��username���� null  
		// ����username ���ı���û���κ�ֵ, ""
		if(username == null || username.equals("")) {
			error = "�û�������Ϊ��";
		}
		if(userpass == null || userpass.equals("")) {
			error = "���벻��Ϊ��";
		}
		// �ж���֤���Ƿ���ȷ...
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)) {
			error = "��֤�벻��ȷ";
		}
		
		User user = userService.findUserByUsernameAndUserPass(username, userpass);
		if(user == null) {
			error = "�û������ڻ����벻��ȷ";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			//���errorΪnull˵��������֤��ͨ��
			if(error!=null){
				String json = new Gson().toJson(error);
				response.getWriter().print(json);
			}else{
				// ��session�洢һ����¼���, ����user������Ϊ��¼���
				ActionContext.getContext().getSession().put("user", user);
				String json = new Gson().toJson(user);
				response.getWriter().print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
