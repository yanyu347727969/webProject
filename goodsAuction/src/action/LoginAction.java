package action;

import service.UserService;

import com.opensymphony.xwork2.ActionContext;

import entity.User;


public class LoginAction {
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private String username;//用户名
	private String userpass;//密码
	private String captcha;//验证码
	private String error;//错误信息
	
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
		// 根本没传username参数 null  
		// 传了username 但文本框没填任何值, ""
		if(username == null || username.equals("")) {
			error = "用户名不能为空";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		if(userpass == null || userpass.equals("")) {
			error = "密码不能为空";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		// 判断验证码是否正确...
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)) {
			error = "验证码不正确";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		
		User user = userService.findUserByUsernameAndUserPass(username, userpass);
		System.out.println(user);
		if(user == null) {
			error = "用户不存在或密码不正确";
			ActionContext.getContext().getSession().put("error", error);
			return "error";
		}
		
		// 向session存储一个登录标记, 利用user对象作为登录标记
		ActionContext.getContext().getSession().put("user", user);
		ActionContext.getContext().getSession().remove("error");//当验证成功时,在跳转前清除session中的错误信息
		// 检查都通过了
		return "success";
	}
	
}
