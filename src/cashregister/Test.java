package cashregister;

public class Test {
	
	public static void main(String[] args ) throws Exception{
		//��ȡ��������
		Cash.getdiscountItem(); //������Ʒ������
		Cash.getFreeOfferItem();//�������һ��Ʒ������
		Cash.getItemInfoList(); //������Ʒ��Ϣ��
		
		CashRegister cr = new CashRegister();
		cr.printList();//��ӡСƱ
	}
}
