package action.ownerCentre;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.ItemService;
import service.UserService;

import com.opensymphony.xwork2.ActionContext;

import entity.User;

/**
 * �û��޸ĸ�����Ϣ��action
 * @author Administrator
 *
 */
public class PersonalInformationUpdateAction {
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private Integer user_id;
	private String username;
	private String userpass;
	private String newPassword;
	private String newPassword2;
	private String mobile;
	private String email;
	private String captcha;
	private String error;
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassword2() {
		return newPassword2;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	
	public String execute(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		// �ж���֤���Ƿ���ȷ...
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)) {
			error = "��֤�벻��ȷ";
			print(response,error);
			return null;
		}
		User user = userService.findUserById(user_id);
		user.setMobile(mobile);//���õ绰����
		user.setEmail(email);//����email
		if(userpass==null&&newPassword==null&&newPassword2==null){//˵��û���޸�����
			
		}else{//˵�����޸�����
			if(!userpass.equals(user.getUserpass())){
				error = "�������������!";
				print(response,error);
				return null;
			}
			if(newPassword==null||newPassword.equals("")){
				error = "������Ĳ���Ϊ��!";
				print(response,error);
				return null;
			}
			if(!newPassword.equals(newPassword2)){
				error = "��������������벻һ��!";
				print(response,error);
				return null;
			}
			user.setUserpass(newPassword);//���û������滻���µ�����
		}
		userService.updateUser(user);
		ActionContext.getContext().getSession().put("user", user);//����session�е�user
		print(response,"�ѳɹ��޸ĸ�����Ϣ!");
		return null;
	}
	private void print(HttpServletResponse response,String error){
		try {
			response.getWriter().print(error);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
