package cashregister;
import java.math.BigDecimal;

public class CashDiscount extends CashSuper{

	private double moneyRebate = 0.95;//折扣率

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
		System.out.println("名称：" + itemName + "，数量:" + itemNum + unit+"，单价：" + unitPrice + "(元)，小计：" + acceptCash() + "(元)" + "，节省" + save() + "元");
	}
	
	public void printMoreInfo(){
	}
	
	public double save(){
		BigDecimal a1 = BigDecimal.valueOf(1);
		BigDecimal a2 = BigDecimal.valueOf(moneyRebate);		
		return unitPrice * itemNum * a1.subtract(a2).doubleValue();
	}
	
	public  static void main(String[] args){
		CashDiscount cd = new CashDiscount("羽毛球",5,"个",1.00);
		cd.printInfo();//预期输出   ”名称：羽毛球，数量:5个，单价：1.0(元，小计：)4.75(元)，节省0.25元"	
	}
}
