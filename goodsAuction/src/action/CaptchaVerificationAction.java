package action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
/**
 * ��֤��֤���Ƿ�������ȷ
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
		String captcha1 = (String) ActionContext.getContext().getSession().get("captcha");//��������������֤��
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
