package action.admin;

import service.KindService;
import entity.Kind;

/**
 * 管理员发布物品种类
 * @author Administrator
 *
 */
public class AddItemKindAction {
	@SuppressWarnings("unused")
	private KindService kindService;
	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	}
	private String kind_name;//种类名称
	private String kind_desc;//种类描述
	public String getKind_name() {
		return kind_name;
	}
	public void setKind_name(String kind_name) {
		this.kind_name = kind_name;
	}
	public String getKind_desc() {
		return kind_desc;
	}
	public void setKind_desc(String kind_desc) {
		this.kind_desc = kind_desc;
	}
	
	
	public String execute(){
		//数据验证
		
		
		//将数据插入数据库
		Kind kind = new Kind();
		kind.setKind_name(kind_name);
		kind.setKind_desc(kind_desc);
		
		kindService.addKind(kind);
		
		
		return "success";
	}

}
