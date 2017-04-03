package action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

import entity.User;
import service.UserService;

/**
 * 使用ajax登录的action
 * @author Administrator
 *
 */
public class AjaxLoginAction {
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
	
	public String execute(){
		// 根本没传username参数 null  
		// 传了username 但文本框没填任何值, ""
		if(username == null || username.equals("")) {
			error = "用户名不能为空";
		}
		if(userpass == null || userpass.equals("")) {
			error = "密码不能为空";
		}
		// 判断验证码是否正确...
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)) {
			error = "验证码不正确";
		}
		
		User user = userService.findUserByUsernameAndUserPass(username, userpass);
		if(user == null) {
			error = "用户不存在或密码不正确";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			//如果error为null说明数据验证不通过
			if(error!=null){
				String json = new Gson().toJson(error);
				response.getWriter().print(json);
			}else{
				// 向session存储一个登录标记, 利用user对象作为登录标记
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
