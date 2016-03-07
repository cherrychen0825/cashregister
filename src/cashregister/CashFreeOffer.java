package cashregister;
public class CashFreeOffer extends CashSuper{
	private int freeNum = 2;//买二送一
	
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
		System.out.println("名称：" + itemName + "，数量:" + itemNum + unit + "，单价：" + unitPrice + "(元)，小计：" + acceptCash() + "(元)");
	}

	public void printMoreInfo(){
		System.out.println("名称：" + itemName + "，数量:" + itemNum /(freeNum+1) + unit);
	}
	
	public double save(){
		return itemNum /(freeNum+1)* unitPrice;
	}
	
	//unit-test
	public  static void main(String[] args){
		CashFreeOffer cfo = new CashFreeOffer("羽毛球",5,"个",1.00);
		cfo.printInfo();//预期输出   ”名称：羽毛球，数量:5个，单价：1.0(元，小计：)4.0(元)"
		cfo.printMoreInfo();//预期输出"名称：羽毛球，数量:1个"
		System.out.println(cfo.save());//预期输出"1.0"
	}
}