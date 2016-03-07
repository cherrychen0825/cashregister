package cashregister;

public class Test {
	
	public static void main(String[] args ) throws Exception{
		//读取超市数据
		Cash.getdiscountItem(); //打折商品名单；
		Cash.getFreeOfferItem();//打买二赠一商品名单；
		Cash.getItemInfoList(); //所有商品信息；
		
		CashRegister cr = new CashRegister();
		cr.printList();//打印小票
	}
}
