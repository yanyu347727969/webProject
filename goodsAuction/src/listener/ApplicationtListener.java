package listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.Kind;
import entity.State;

import service.KindService;
import service.StateService;
import service.UserService;
/**
 * 用于监听application的创建,在创建时将物品种类kinds和物品状态states放入application
 * @author Administrator
 *
 */
public class ApplicationtListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application = servletContextEvent.getServletContext();  
		ApplicationContext spring = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		KindService ks = (KindService) spring.getBean("kindService");
		List<Kind> kinds = ks.findAllKind();
		StateService ss = (StateService) spring.getBean("stateService");
		List<State> states = ss.findAllState();
		application.setAttribute("kinds", kinds);
		application.setAttribute("states", states);
	}

}
