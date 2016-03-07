package cashregister;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cash {
	//���ݿ�����
	static ArrayList<String> itemInfoList = new ArrayList<String>(); //����������Ʒ ��Ϣ   
	static ArrayList<String> freeOfferItem= new ArrayList<String>(); //���вμ����ͻ����Ʒ
	static ArrayList<String> discountItem = new ArrayList<String>(); //���вμӴ��ۻ����Ʒ
             
    
    //������Ʒ��Ϣ    
    String item_name;//��Ʒ����
    String item_unit;//��Ʒ������λ
    double item_unitPrice;//��Ʒ����
    int itemNum;//��Ʒ�������
    
    CashSuper cs = null;
    public int type ;

    public Cash(String item,int itemNum) throws Exception{
    	this.itemNum = itemNum ;
    	gettype(item);    //��ȡ��Ʒ���ѹ���
    	getItemInfo(item);//��ȡ��Ʒ���Ƶ���Ϣ
    	switch(type){
    	case 0: //�����շ�
    		cs = new CashNormal(item_name,itemNum,item_unit,item_unitPrice);
    		break;
    	case 1:	//���ۻ
    		cs = new CashDiscount(item_name,itemNum,item_unit,item_unitPrice);
    		break;
    	case 2:	//���ͻ
    		cs = new CashFreeOffer(item_name,itemNum,item_unit,item_unitPrice);
    		break;
    	case 3:	//ͬʱ�������ֻ
    		cs = new CashFreeOffer(item_name,itemNum,item_unit,item_unitPrice);	
    	}	
   	
    }
   
    //�ж���Ʒ�Ƿ������ͻ�嵥������0��ʾ�񣬷���1��ʾ�ǡ�
    public void gettype(String item) throws Exception{
    	int isFreeOffer = 0;
    	int isDiscount  = 0;
    	//getItemInfoList();
    	//getdiscountItem();
    	//getFreeOfferItem();
    	for(int i = 0;i<freeOfferItem.size();i++){//�����������
    		if(freeOfferItem.get(i).contains(item)){
    			isFreeOffer = 1;//�ҵ��˾ͷ�����
    			break;
    		}
    	}
    	for(int i = 0;i<discountItem.size();i++){//�����������
    		if(discountItem.get(i).contains(item)){
    			isDiscount =1;//�ҵ��˾ͷ�����
    			break;
    		}
    	}    	
    	type = isFreeOffer * 2 + isDiscount;
    }
    
    
    //��ȡ����Ʒ�����ƣ����ۣ�������λ
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

    
    //������Ʒ�ܼ�
    public double totalPrice(){
    	return cs.acceptCash();
    }
    
    //��ӡ��Ʒ������Ϣ
    public void printInfo(){
    	 cs.printInfo();
    }
    
    public void printMoreInfo(){
    	 cs.printMoreInfo();
    }
    
    public double  save(){
   		return cs.save();
    }
   
  //��ȡ�������в�Ʒ��Ϣ
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
    
    //��ȡ���вμ����ͻ����Ʒ
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

	//��ȡ���вμӴ��ۻ����Ʒ
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
		//cash.getdiscountItem();//Ԥ�����   �����ƣ���ë������:5�������ۣ�1.0(Ԫ)��С�ƣ�5.0(Ԫ)	"
		System.out.println(Cash.discountItem);
		//cash.getFreeOfferItem();
		System.out.println(Cash.freeOfferItem);
		//cash.getItemInfoList();
		//System.out.println(cash.itemInfoList);
		//cash.getItemInfo("ITEM000003");
		//System.out.println(cash.item_name + cash.item_unitPrice + cash.item_unit);
	}
}
