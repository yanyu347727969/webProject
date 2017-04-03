package dao;

import java.util.List;

import entity.Kind;

public interface KindDao {
	
	//������Ʒ���
	public Integer addKind(Kind kind);
	
	//ɾ����Ʒ���
	public void deleteKind(Kind kind);

	
	//�޸���Ʒ�����Ϣ
	public void updateKind(Kind kind);
	
	
	//��ѯ��Ʒ���
	public Kind findKindById(int kind_id);
	
	//��ѯ������Ʒ���
	public List<Kind> findAllKind();
	
	//ͨ���������ѯ��Ʒ���
	public Kind findKindByKind_name(String kind_name);
}
