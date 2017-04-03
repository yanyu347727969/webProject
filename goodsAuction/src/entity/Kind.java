package entity;

import java.io.Serializable;

/*
 * 
 * ��Ʒ���ʵ��
 * 
 */
@SuppressWarnings("serial")
public class Kind implements Serializable {

	private Integer kind_id;//�����
	private String kind_name;//�������
	private String kind_desc;//�������
	
	public Kind() {
		super();
	}

	public Kind(Integer kind_id, String kind_name, String kind_desc) {
		super();
		this.kind_id = kind_id;
		this.kind_name = kind_name;
		this.kind_desc = kind_desc;
	}

	public Integer getKind_id() {
		return kind_id;
	}

	public void setKind_id(Integer kind_id) {
		this.kind_id = kind_id;
	}

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

	@Override
	public String toString() {
		return "Kind [kind_id=" + kind_id + ", kind_name=" + kind_name
				+ ", kind_desc=" + kind_desc + "]";
	}
}
