package service;

import java.util.List;

import entity.Kind;

public interface KindService {
	
	//������
	public Integer addKind(Kind kind);
	
	
	//ɾ�����
	public void deleteKind(Kind kind);
	
	
	//�������
	public void updateKind(Kind kind);
	
	
	//��ѯ���
	public Kind findKindById(int kind_id);
	
	
	//��ѯ�������
	public List<Kind> findAllKind();
	
	
	//ͨ���������ѯ���
	public Kind findKindByKind_name(String kind_name);
	
	
}
