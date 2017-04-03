package action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
/**
 * 验证验证码是否输入正确
 * @author Administrator
 *
 */
public class CaptchaVerificationAction {
	private String captcha;
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	public String execute(){
		String captcha1 = (String) ActionContext.getContext().getSession().get("captcha");//服务器产生的验证码
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(captcha.equals(captcha1)){
				response.getWriter().print("right");
			}else{
				response.getWriter().print("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
