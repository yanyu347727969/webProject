package action;

import java.io.File;

import entity.Item;
import service.ItemService;

/**
 * 用于展示物品详情
 * @author Administrator
 *
 */
public class ItemDetailAction {
	private static final String PATH = "webapps/ROOT/onload/";
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private int count;//上传图片的张数
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
		//统计该商品上传图片的数量
		String[] strings = System.getProperty("user.dir").split("\\\\");//获得当前项目的所在目录
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
		
		//查询该商品的详细信息
		item = itemService.findItemById(item_id);
		return "success";
	}

}
