package action;

import com.opensymphony.xwork2.ActionContext;

import entity.User;
import service.UserService;

public class RegisterAction {
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private String username;
	private String userpass;
	private String userpass2;
	private String email;
	private String mobile;
	private String error;
	private String captcha;//验证码
	
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
	public String getUserpass2() {
		return userpass2;
	}
	public void setUserpass2(String userpass2) {
		this.userpass2 = userpass2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
	public String execute(){
		// 判断验证码是否正确...
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)) {
			error = "验证码不正确";
			return "error";
		}
		userService.addUser(new User(0, username, userpass, email, mobile));
		
		return "success";
	}

}
