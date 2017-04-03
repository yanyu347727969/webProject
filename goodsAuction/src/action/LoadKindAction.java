package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import entity.Kind;
import service.KindService;

/**
 * 加载物品类别
 * @author Administrator
 *
 */
public class LoadKindAction {
	private KindService kindService;

	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	} 
	public String execute(){
		List<Kind> list = kindService.findAllKind();
		String json = new Gson().toJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
