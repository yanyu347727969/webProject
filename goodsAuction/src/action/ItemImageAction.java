package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
/**
 * ������Ӧ��ҳ��ÿ����Ʒ��ͼƬ
 * @author Administrator
 *
 */
public class ItemImageAction {
	private static final String PATH = "webapps/ROOT/onload/";
	private Integer item_id;//��Ҫ��ѯͼƬ����Ʒ��id
	private int image_id;//��ѯ�ڼ���ͼƬ
	private String size;//��ѯ���Ǵ�ͼ����Сͼ
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String execute(){
		String[] strings = System.getProperty("user.dir").split("\\\\");//��õ�ǰ��Ŀ������Ŀ¼
		String path = "";
		for(String str : strings){
			if(!"bin".equals(str)){
				path +=str;
				path +="/";
			}
		}
		path += PATH;//���ͼƬ�ڷ������ϴ����λ��
		path=path+item_id+"/"+size;//�����Ʒitem_id��·��
		File file = new File(path);
		File[] files = file.listFiles();
		
		if(files==null||files.length==0){//�������Ʒ��û���ϴ�ͼƬ,����ʾĬ�ϵ�ͼƬ
			path = path.substring(0, path.lastIndexOf("/"));
			path = path.substring(0, path.lastIndexOf("/"))+"/sample.jpg";
			files = new File[1];
			files[0] = new File(path);
		}
		
		
		//���ͼƬ
		BufferedInputStream bis = null;
		BufferedOutputStream bos= null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			InputStream is = new FileInputStream(files[image_id]);
			bis = new BufferedInputStream(is);
			OutputStream out = response.getOutputStream();
			bos = new BufferedOutputStream(out);
			
			byte[] b = new byte[4096];
			while(bis.read(b)!=-1){
				bos.write(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(bis!=null) bis.close();
				if(bos!=null) bos.flush();
				if(bos!=null) bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
