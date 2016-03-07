package cashregister;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cash {
	//数据库资料
	static ArrayList<String> itemInfoList = new ArrayList<String>(); //超市所有商品 信息   
	static ArrayList<String> freeOfferItem= new ArrayList<String>(); //超市参加赠送活动的商品
	static ArrayList<String> discountItem = new ArrayList<String>(); //超市参加打折活动的商品
             
    
    //购买商品信息    
    String item_name;//商品名称
    String item_unit;//商品计数单位
    double item_unitPrice;//商品单价
    int itemNum;//商品购买个数
    
    CashSuper cs = null;
    public int type ;

    public Cash(String item,int itemNum) throws Exception{
    	this.itemNum = itemNum ;
    	gettype(item);    //获取商品付费规则
    	getItemInfo(item);//获取商品名称等信息
    	switch(type){
    	case 0: //正常收费
    		cs = new CashNormal(item_name,itemNum,item_unit,item_unitPrice);
    		break;
    	case 1:	//打折活动
    		cs = new CashDiscount(item_name,itemNum,item_unit,item_unitPrice);
    		break;
    	case 2:	//赠送活动
    		cs = new CashFreeOffer(item_name,itemNum,item_unit,item_unitPrice);
    		break;
    	case 3:	//同时满足两种活动
    		cs = new CashFreeOffer(item_name,itemNum,item_unit,item_unitPrice);	
    	}	
   	
    }
   
    //判断商品是否在赠送活动清单，返回0表示否，返回1表示是。
    public void gettype(String item) throws Exception{
    	int isFreeOffer = 0;
    	int isDiscount  = 0;
    	//getItemInfoList();
    	//getdiscountItem();
    	//getFreeOfferItem();
    	for(int i = 0;i<freeOfferItem.size();i++){//遍历数组查找
    		if(freeOfferItem.get(i).contains(item)){
    			isFreeOffer = 1;//找到了就返回是
    			break;
    		}
    	}
    	for(int i = 0;i<discountItem.size();i++){//遍历数组查找
    		if(discountItem.get(i).contains(item)){
    			isDiscount =1;//找到了就返回是
    			break;
    		}
    	}    	
    	type = isFreeOffer * 2 + isDiscount;
    }
    
    
    //获取该商品的名称，单价，计量单位
    public void getItemInfo(String item){
    	for(int i=0 ; i < itemInfoList.size() ; i++){
    		String item_info = itemInfoList.get(i);
    		if (item_info.contains(item)){
    			this.item_name = item_info .substring(item_info .indexOf("itemName")+12 ,item_info .indexOf("itemPrice")-4);
    			this.item_unitPrice = Double.parseDouble(item_info .substring(item_info .indexOf("itemPrice")+13 ,item_info .indexOf("itemUnit")-5));
    			this.item_unit = item_info .substring(item_info .indexOf("itemUnit")+12 ,item_info .indexOf("}") - 1);   
    		}
    	}
    }

    
    //计算商品总价
    public double totalPrice(){
    	return cs.acceptCash();
    }
    
    //打印商品购物信息
    public void printInfo(){
    	 cs.printInfo();
    }
    
    public void printMoreInfo(){
    	 cs.printMoreInfo();
    }
    
    public double  save(){
   		return cs.save();
    }
   
  //获取超市所有产品信息
    public static void getItemInfoList() throws Exception{
		BufferedReader bf= new BufferedReader(new FileReader("itemInfo.txt"));
		String line= null;
		while(( line= bf.readLine()) != null){
	    	if (line.equals("[" )|| line.equals("]" ))
	    		continue;
	    	itemInfoList.add(line);
		}
		bf.close();
   }
    
    //获取超市参加赠送活动的商品
	public static void getFreeOfferItem() throws Exception{
		BufferedReader bf= new BufferedReader(new FileReader("freeOfferItem.txt"));
		String line= null;
		while(( line= bf.readLine()) != null){
	    	if (line.equals("[") || line.equals("]"))
	    		continue;
			freeOfferItem.add(line);
		}
		bf.close();
   }

	//获取超市参加打折活动的商品
	public static void getdiscountItem() throws IOException{
		BufferedReader bf= new BufferedReader(new FileReader("discountItem.txt"));
		String line= null;
		while(( line= bf.readLine()) != null){
	    	if (line.equals("[")||line.equals("]") )
	    		continue;
			discountItem.add(line);		
	   }
		bf.close();			
	}
	
	//unit-test
	public  static void main(String[] args) throws Exception{
		Cash.getdiscountItem();
		Cash.getFreeOfferItem();
		Cash.getItemInfoList();
		Cash cash = new Cash("ITEM000001",6);
		System.out.println(cash.type);
		cash.printInfo();
		cash.printMoreInfo();
		System.out.println(cash.save());
		//cash.getdiscountItem();//预期输出   ”名称：羽毛球，数量:5个，单价：1.0(元)，小计：5.0(元)	"
		System.out.println(Cash.discountItem);
		//cash.getFreeOfferItem();
		System.out.println(Cash.freeOfferItem);
		//cash.getItemInfoList();
		//System.out.println(cash.itemInfoList);
		//cash.getItemInfo("ITEM000003");
		//System.out.println(cash.item_name + cash.item_unitPrice + cash.item_unit);
	}
}
