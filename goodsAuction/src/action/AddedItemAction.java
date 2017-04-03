package action;

import java.util.Date;

import com.opensymphony.xwork2.ActionContext;

import entity.Item;
import entity.Kind;
import entity.State;
import entity.User;

import service.ItemService;
import service.KindService;
import service.StateService;

public class AddedItemAction {
	
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private KindService kindService;
	public void setKindService(KindService kindService) {
		this.kindService = kindService;
	}
	private StateService stateService;
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	private Integer item_id;//��Ʒid
	private String item_name;//��Ʒ����
	private String item_desc;//��Ʒ����
	private Date addtime;//����ʱ��
	private Date endtime;//����ʱ��
	private double init_price;//���ļ۸�
	private int kind_id;//���
	private String captcha;//��֤��
	private String error;//������Ϣ
	private String address;//ʵ�ʽ��׵�ַ
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public double getInit_price() {
		return init_price;
	}
	public void setInit_price(double init_price) {
		this.init_price = init_price;
	}
	public int getKind_id() {
		return kind_id;
	}
	public void setKind_id(int kind_id) {
		this.kind_id = kind_id;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String execute(){
		//�ж���֤�������Ƿ���ȷ
		String str = (String) ActionContext.getContext().getSession().get("captcha");
		if(!str.equalsIgnoreCase(captcha)){
			error="��֤�����벻��ȷ";
			return "error";
		}
		//�����Ʒӵ���û�����
		User owner_user = (User) ActionContext.getContext().getSession().get("user");
		//�����Ʒ������
		Kind kind = kindService.findKindById(kind_id);
		//�����Ʒ״̬����
		State state = null;
		long start = addtime.getTime();//����ʱ��
		long now = new Date().getTime();//��ǰʱ��
		long end =endtime.getTime();//����ʱ�� 
		//����ʱ��<=��ǰʱ��<����ʱ��ʱ,��Ʒ״̬Ϊ"����������"
		if(start<=now&&now<end){
			state = stateService.findStateByState_name("����������");
		}
		//����ʱ��>����ʱ��>��ǰʱ��,��Ʒ״̬Ϊ"׼��������"
		else if(end>start&&start>now){
			state = stateService.findStateByState_name("׼��������");
		}
		//����ʱ��<����ʱ��<=��ǰʱ��,��Ʒ״̬Ϊ"�ѽ�������"
		else if(start<end&&end<=now){
			state = stateService.findStateByState_name("�ѽ�������");
		}
		//������Ϊerror
		else{
			error = "����ʱ������ʱ�����";
			return "error";
		}
		Item item = new Item(null, item_name, item_desc, addtime,
				endtime, init_price, null,init_price, owner_user, kind, null, state, null,address);
		item_id = itemService.addItem(item);
		return "success";
	}
	
}
