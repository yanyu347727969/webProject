package entity;

import java.io.Serializable;

/*
 * 
 * 物品类别实体
 * 
 */
@SuppressWarnings("serial")
public class Kind implements Serializable {

	private Integer kind_id;//类别编号
	private String kind_name;//类别名称
	private String kind_desc;//类别描述
	
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
