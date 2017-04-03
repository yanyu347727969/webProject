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
	private String[] imageFileName; // �洢���ϴ��ļ���ԭʼ�ļ��� 
	private String[] imageContentType; // �洢���ϴ��ļ�������  image/jpeg, text/html
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
		String[] strings = System.getProperty("user.dir").split("\\\\");//��õ�ǰ��Ŀ������Ŀ¼
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
				//�����û��ϴ���ͼƬ
				String ext = imageFileName[i].substring(imageFileName[i].lastIndexOf(".")); // ��׺
				filename[i] = DigestUtils.md5Hex(new FileInputStream(image[i]))+ext;//�ļ���
				//User user = (User) ActionContext.getContext().getSession().get("user");//���û���Ψһ��ʶuser_id��Ϊ�ļ�����
				//String foldername = DigestUtils.md5Hex(item_id.toString());//����Ʒ��id��Ϊ�ļ�����,���ļ����µ����е�ͼƬ���Ǹ���Ʒ��ͼƬ
				//String foldername = DigestUtils.md5Hex(user.getUser_id().toString());//�û���Ӧ��ͼƬ���ڵ��ļ�����
				String bigImageName = path+item_id+"/bigimage/"+filename[i];//ֱ����item_id��Ϊ�ļ�������,
				String smallImageName = path+item_id+"/smallimage/"+filename[i];
				FileUtils.copyFile(image[i], new File(bigImageName)); // �ڲ�ʹ���� nio, Ч���ڸ߲����¸���
				File smallImage = new File(smallImageName);
				/*if(!smallImage.exists()){
					smallImage.createNewFile();
				}*/
				//ImageUtil.compressImage(image[i], 350, 350, 0.75f, false, smallImage);
				ImageUtil.resize(image[i], smallImage, 350, 350, 1f);
				//��ͼƬ�Ĵ���·���������ݿ���
				/*Item item = itemService.findItemById(item_id);
				item.setImagePath(fullName);
				itemService.updateItem(item);*/
			}
			return "success";//�ϴ��ɹ�
		} catch (Exception e) {
			return "error";//�ϴ�ʧ��
		}
	}
}
