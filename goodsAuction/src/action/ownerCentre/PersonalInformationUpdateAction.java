package action.ownerCentre;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.ItemService;
import service.UserService;

import com.opensymphony.xwork2.ActionContext;

import entity.User;

/**
 * 用户修改个人信息的action
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

		// 判断验证码是否正确...
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)) {
			error = "验证码不正确";
			print(response,error);
			return null;
		}
		User user = userService.findUserById(user_id);
		user.setMobile(mobile);//设置电话号码
		user.setEmail(email);//设置email
		if(userpass==null&&newPassword==null&&newPassword2==null){//说明没有修改密码
			
		}else{//说明有修改密码
			if(!userpass.equals(user.getUserpass())){
				error = "旧密码输入错误!";
				print(response,error);
				return null;
			}
			if(newPassword==null||newPassword.equals("")){
				error = "新密码的不能为空!";
				print(response,error);
				return null;
			}
			if(!newPassword.equals(newPassword2)){
				error = "新密码的两次输入不一致!";
				print(response,error);
				return null;
			}
			user.setUserpass(newPassword);//将用户密码替换成新的密码
		}
		userService.updateUser(user);
		ActionContext.getContext().getSession().put("user", user);//更新session中的user
		print(response,"已成功修改个人信息!");
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
