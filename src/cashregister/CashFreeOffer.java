package cashregister;
public class CashFreeOffer extends CashSuper{
	private int freeNum = 2;//�����һ
	
	public CashFreeOffer(String itemName,int itemNum,String unit,double unitPrice){
		this.itemName  = itemName;
		this.itemNum   = itemNum;
		this.unit      = unit;
		this.unitPrice = unitPrice;
	}

	public double acceptCash(){		
		return itemNum /(freeNum+1) * freeNum * unitPrice + itemNum % (freeNum+1) * unitPrice;
	}
	
	public void printInfo(){
		System.out.println("���ƣ�" + itemName + "������:" + itemNum + unit + "�����ۣ�" + unitPrice + "(Ԫ)��С�ƣ�" + acceptCash() + "(Ԫ)");
	}

	public void printMoreInfo(){
		System.out.println("���ƣ�" + itemName + "������:" + itemNum /(freeNum+1) + unit);
	}
	
	public double save(){
		return itemNum /(freeNum+1)* unitPrice;
	}
	
	//unit-test
	public  static void main(String[] args){
		CashFreeOffer cfo = new CashFreeOffer("��ë��",5,"��",1.00);
		cfo.printInfo();//Ԥ�����   �����ƣ���ë������:5�������ۣ�1.0(Ԫ��С�ƣ�)4.0(Ԫ)"
		cfo.printMoreInfo();//Ԥ�����"���ƣ���ë������:1��"
		System.out.println(cfo.save());//Ԥ�����"1.0"
	}
}