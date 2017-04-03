package action;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import service.ItemService;
import util.ImageUtil;

import com.opensymphony.xwork2.ActionContext;

import entity.Item;
import entity.User;

public class SaveImageAction {
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	private static final String PATH = "webapps/ROOT/onload/";
	private File[] image;
	private String[] imageFileName; // 存储了上传文件的原始文件名 
	private String[] imageContentType; // 存储了上传文件的类型  image/jpeg, text/html
	private String[] filename;
	private Integer item_id;

	public File[] getImage() {
		return image;
	}
	public void setImage(File[] image) {
		this.image = image;
	}
	public String[] getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String[] getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String[] getFilename() {
		return filename;
	}
	public void setFilename(String[] filename) {
		this.filename = filename;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	
	public String execute(){
		filename = new String[image.length];
		String[] strings = System.getProperty("user.dir").split("\\\\");//获得当前项目的所在目录
		String path = "";
		for(String str : strings){
			if(!"bin".equals(str)){
				path +=str;
				path +="/";
			}
		}
		path += PATH;
		try {
			for(int i=0;i<image.length;i++){
				//储存用户上传的图片
				String ext = imageFileName[i].substring(imageFileName[i].lastIndexOf(".")); // 后缀
				filename[i] = DigestUtils.md5Hex(new FileInputStream(image[i]))+ext;//文件名
				//User user = (User) ActionContext.getContext().getSession().get("user");//将用户的唯一标识user_id作为文件夹名
				//String foldername = DigestUtils.md5Hex(item_id.toString());//将物品的id作为文件夹名,该文件夹下的所有的图片都是该物品的图片
				//String foldername = DigestUtils.md5Hex(user.getUser_id().toString());//用户对应的图片所在的文件夹名
				String bigImageName = path+item_id+"/bigimage/"+filename[i];//直接用item_id做为文件夹名字,
				String smallImageName = path+item_id+"/smallimage/"+filename[i];
				FileUtils.copyFile(image[i], new File(bigImageName)); // 内部使用了 nio, 效率在高并发下更好
				File smallImage = new File(smallImageName);
				/*if(!smallImage.exists()){
					smallImage.createNewFile();
				}*/
				//ImageUtil.compressImage(image[i], 350, 350, 0.75f, false, smallImage);
				ImageUtil.resize(image[i], smallImage, 350, 350, 1f);
				//将图片的储存路径存入数据库中
				/*Item item = itemService.findItemById(item_id);
				item.setImagePath(fullName);
				itemService.updateItem(item);*/
			}
			return "success";//上传成功
		} catch (Exception e) {
			return "error";//上传失败
		}
	}
}
