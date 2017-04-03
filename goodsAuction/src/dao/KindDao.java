package dao;

import java.util.List;

import entity.Kind;

public interface KindDao {
	
	//增加物品类别
	public Integer addKind(Kind kind);
	
	//删除物品类别
	public void deleteKind(Kind kind);

	
	//修改物品类别信息
	public void updateKind(Kind kind);
	
	
	//查询物品类别
	public Kind findKindById(int kind_id);
	
	//查询所有物品类别
	public List<Kind> findAllKind();
	
	//通过类别名查询物品类别
	public Kind findKindByKind_name(String kind_name);
}
