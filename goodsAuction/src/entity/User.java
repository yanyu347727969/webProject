package entity;

import java.io.Serializable;

/*
 * 
 * 用户实体类
 * 
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	
	private Integer user_id;//用户编号
	private String username;//用户名
	private String userpass;//密码
	private String email;//邮箱
	private String mobile;//手机号码
	
	public User() {
		super();
	}

	public User(Integer user_id, String username, String userpass,
			String email, String mobile) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.userpass = userpass;
		this.email = email;
		this.mobile = mobile;
	}

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

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username
				+ ", userpass=" + userpass + ", email=" + email + ", mobile="
				+ mobile + "]";
	}
}
