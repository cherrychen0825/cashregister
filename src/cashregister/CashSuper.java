package cashregister;
/**

* �������

* @author Chen Jun

* @Time 2016-03-02

*

*/


public abstract class CashSuper {	
	public String itemName;//��Ʒ����
	public int itemNum;//��Ʒ��������
	public String unit;//��Ʒ������λ
	public double unitPrice;//��Ʒ����
	
	public abstract double acceptCash();
	public abstract void printInfo();
	public abstract void printMoreInfo();
	public abstract double save();
}
