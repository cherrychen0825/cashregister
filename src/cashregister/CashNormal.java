package cashregister;
public class CashNormal extends CashSuper{

	public CashNormal(String itemName,int itemNum,String unit,double unitPrice){
		this.itemName  = itemName;
		this.itemNum   = itemNum;
		this.unit      = unit;
		this.unitPrice = unitPrice;
	}
	
	public double acceptCash(){
		return unitPrice * itemNum ;
	}
	
	public void printInfo(){
		System.out.println("���ƣ�" + itemName + "������:" + itemNum +unit+"�����ۣ�" + unitPrice + "(Ԫ)��С�ƣ�" + acceptCash() + "(Ԫ)");
	}
	
	public void printMoreInfo(){
		
	}
	
	public double save(){
		return 0;
	}
	
	//unit-test
	public  static void main(String[] args){
		CashNormal cn = new CashNormal("��ë��",5,"��",1.00);
		cn.printInfo();//Ԥ�����   �����ƣ���ë������:5�������ۣ�1.0(Ԫ)��С�ƣ�5.0(Ԫ)	"	
	}
}
