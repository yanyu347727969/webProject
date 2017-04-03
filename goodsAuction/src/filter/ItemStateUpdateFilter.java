package filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.opensymphony.xwork2.ActionContext;

import entity.Bid;
import entity.Item;
import entity.State;
import entity.User;

import service.BidService;
import service.ItemService;
import service.StateService;
import service.UserService;

/**
 * �ù������������ǵ�������Ʒ�����ݵ�ʱ������ȷ��Ʒ������״̬
 * @author Administrator
 *
 */
public class ItemStateUpdateFilter implements Filter {
	private StateService stateService;
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	private ItemService itemService;
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	private BidService bidService;
	public void setBidService(BidService bidService) {
		this.bidService = bidService;
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("---------------����������Ʒ��ϢУ�������---------------");
		long now = new Date().getTime();//��ǰϵͳʱ��
		State stateNow = stateService.findStateByState_name("����������");
		State stateEnd = stateService.findStateByState_name("�ѽ�������");
		
		List<Item> items0 = itemService.findItemByState_name("׼��������");//��ѯ"׼��������"����Ʒ
		//����"׼��������"����Ʒ
		for (Item item : items0) {
			//�����ǰʱ����ڵ��ڿ�ʼʱ����С�ڵ��ڽ���ʱ��,��˵����Ʒ�������� ,��Ҫ����Ʒ״̬�޸�Ϊ"����������"
			if(item.getAddtime().getTime()<=now&&now<=item.getEndtime().getTime()){
				item.setState(stateNow);
				itemService.updateItem(item);
			}
			//�����ǰʱ����ڻ��ߵ��ڽ���ʱ��,��˵����Ʒ�Ѿ���������,��Ҫ����Ʒ״̬�޸�Ϊ�ѽ�������
			//����endAuction���������Ѿ�������������Ʒ
			if(now>=item.getEndtime().getTime()){
				endAuction(item,stateEnd);
			}
		}
		
		List<Item> items1 = itemService.findItemByState_name("����������");//��ѯ"����������"����Ʒ
		//����"����������"����Ʒ"
		for (Item item : items1) {
			//�����ǰʱ����ڻ��ߵ��ڽ���ʱ��,��˵����Ʒ�Ѿ���������,��Ҫ����Ʒ״̬�޸�Ϊ�ѽ�������
			if(now>=item.getEndtime().getTime()){
				endAuction(item,stateEnd);
			}				
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	/**
	 * �����Ѿ�����������Ʒ�ķ���
	 * @param items,��Ҫ�������Ʒ����
	 * @param nowTime,��ǰʱ��
	 */
	private void endAuction(Item item,State stateEnd) {
		item.setState(stateEnd);//����"�ѽ�������"
		//�Ѿ�������������Ʒ��Ҫ����Ӯȡ��
		//�õ����ļ۸������Ǹ�����bid
		List<Bid> bids = bidService.findBidByItem_id(item.getItem_id());
		if(!bids.isEmpty()){
			Bid bid = bids.get(0);
			for(int i=1 ; i < bids.size() ; i++){
				if(bids.get(i).getBid_price()>bid.getBid_price()){
					bid = bids.get(i);
				}
			}
			item.setWinner_user(bid.getUser());//���þ���Ӯȡ��
		}
		itemService.updateItem(item);
	}
}
