package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;

import com.opensymphony.xwork2.ActionContext;

import entity.User;

public class LoginVerificationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("---------------进入登录校验过滤器---------------");
		HttpServletRequest request = (HttpServletRequest ) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			chain.doFilter(req, resp);
		}else{
			//用户还未登录
			System.out.println("用户还没有的登录");
			response.sendRedirect("/goodsAuction/jsp/user/login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
