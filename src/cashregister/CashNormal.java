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
		System.out.println("名称：" + itemName + "，数量:" + itemNum +unit+"，单价：" + unitPrice + "(元)，小计：" + acceptCash() + "(元)");
	}
	
	public void printMoreInfo(){
		
	}
	
	public double save(){
		return 0;
	}
	
	//unit-test
	public  static void main(String[] args){
		CashNormal cn = new CashNormal("羽毛球",5,"个",1.00);
		cn.printInfo();//预期输出   ”名称：羽毛球，数量:5个，单价：1.0(元)，小计：5.0(元)	"	
	}
}
