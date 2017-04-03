package service;

import java.util.List;

import entity.Kind;

public interface KindService {
	
	//添加类别
	public Integer addKind(Kind kind);
	
	
	//删除类别
	public void deleteKind(Kind kind);
	
	
	//更新类别
	public void updateKind(Kind kind);
	
	
	//查询类别
	public Kind findKindById(int kind_id);
	
	
	//查询所有类别
	public List<Kind> findAllKind();
	
	
	//通过类别名查询类别
	public Kind findKindByKind_name(String kind_name);
	
	
}
