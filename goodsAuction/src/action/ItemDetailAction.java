package action;

import java.io.File;

import entity.Item;
import service.ItemService;

/**
 * ����չʾ��Ʒ����
 * @author Administrator
 *
 */
public class ItemDetailAction {
	private static final String PATH = "webapps/ROOT/onload/";
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private int count;//�ϴ�ͼƬ������
	private Integer item_id;
	private Item item;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	
	public String execute(){
		//ͳ�Ƹ���Ʒ�ϴ�ͼƬ������
		String[] strings = System.getProperty("user.dir").split("\\\\");//��õ�ǰ��Ŀ������Ŀ¼
		String path = "";
		for(String str : strings){
			if(!"bin".equals(str)){
				path +=str;
				path +="/";
			}
		}
		path += PATH;
		path = path+item_id+"/bigimage";
		File file = new File(path);
		File[] files = file.listFiles();
		if(files!=null){
			count = files.length;
		}else{
			count=1;
		}
		
		//��ѯ����Ʒ����ϸ��Ϣ
		item = itemService.findItemById(item_id);
		return "success";
	}

}
