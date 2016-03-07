package cashregister;
import java.math.BigDecimal;

public class CashDiscount extends CashSuper{

	private double moneyRebate = 0.95;//�ۿ���

	public CashDiscount(String itemName,int itemNum,String unit,double unitPrice){
		this.itemName  = itemName;
		this.itemNum   = itemNum ;
		this.unit      = unit;
		this.unitPrice = unitPrice;
	}
	
	public double acceptCash(){
		return unitPrice * itemNum * moneyRebate;
	}
	
	public void printInfo(){
		System.out.println("���ƣ�" + itemName + "������:" + itemNum + unit+"�����ۣ�" + unitPrice + "(Ԫ)��С�ƣ�" + acceptCash() + "(Ԫ)" + "����ʡ" + save() + "Ԫ");
	}
	
	public void printMoreInfo(){
	}
	
	public double save(){
		BigDecimal a1 = BigDecimal.valueOf(1);
		BigDecimal a2 = BigDecimal.valueOf(moneyRebate);		
		return unitPrice * itemNum * a1.subtract(a2).doubleValue();
	}
	
	public  static void main(String[] args){
		CashDiscount cd = new CashDiscount("��ë��",5,"��",1.00);
		cd.printInfo();//Ԥ�����   �����ƣ���ë������:5�������ۣ�1.0(Ԫ��С�ƣ�)4.75(Ԫ)����ʡ0.25Ԫ"	
	}
}
