package action.admin;

import service.KindService;
import entity.Kind;

/**
 * ����Ա������Ʒ����
 * @author Administrator
 *
 */
public class AddItemKindAction {
	@SuppressWarnings("unused")
	private KindService kindService;
	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	}
	private String kind_name;//��������
	private String kind_desc;//��������
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
		//������֤
		
		
		//�����ݲ������ݿ�
		Kind kind = new Kind();
		kind.setKind_name(kind_name);
		kind.setKind_desc(kind_desc);
		
		kindService.addKind(kind);
		
		
		return "success";
	}

}
