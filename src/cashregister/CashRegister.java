package cashregister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CashRegister {
	ArrayList<String> itemList = new ArrayList<String>();//json数据处理后的条形码与数量信息
	ArrayList<String>     item = new ArrayList<String>();//购买商品每种商品条形码（没有重复）
	ArrayList<Integer> item_num = new ArrayList<Integer>();//购买商品每种商品总数量

	public CashRegister() throws IOException{
		//读取JSON数据
		BufferedReader bf= new BufferedReader(new FileReader("test.txt"));
		String line= null;
		while(( line= bf.readLine()) != null){ 
			//首行和尾行不处理
			if (line.equals("[") || line.equals("]"))continue;
			//提取商品条形码与数量	
			itemList.add(line.substring(line.indexOf("input")+9,line.lastIndexOf("}")-1));
      
		}
		bf.close();
	}

	//获取商品条形码 与购买总数
	public void getItemList(){
		for(int i=0;i<itemList.size();i++){
			int num_temp=1;//扫一次条形码时购买个数。 默认为1。
			String item_now = itemList.get(i);
			//出现-时，扫码的购买个数为-后面的数字
			if(item_now.contains("-")){
				num_temp = Integer.parseInt(item_now.substring(item_now.indexOf("-")+1, item_now.length()));
			} 
			
			//查看正在扫描的条形码在已扫描列表中的下标。返回下标或者-1；
			int i_item = item.indexOf(item_now);			
			//条形码itemList[i]没扫描过时
			if(i_item == -1 ){
				item.add(item_now.substring(0, 10));
				item_num.add(num_temp);	
				continue;
			}
			//条形码itemList[i]扫描过时
			num_temp = item_num.get(i_item)+num_temp;
			item_num.set(i_item,num_temp);	
		}
	}	

	//打印小票
	public void printList() throws Exception{
		getItemList();//获取商品条形码item[]与购买总数item_num[]
		ArrayList<Cash> cash = new ArrayList<Cash>();		
		double saveTatal = 0;
		double priceTatal = 0;
		System.out.println("***<没钱赚商店>购物清单***");
		for(int i = 0;i < item.size();i++  ){
			cash.add(new Cash(item.get(i),item_num.get(i)));
			cash.get(i).printInfo();
			
			BigDecimal a1 = BigDecimal.valueOf(priceTatal);
			BigDecimal a2 = BigDecimal.valueOf(cash.get(i).totalPrice());
			priceTatal = a1.add(a2).doubleValue();
			
			BigDecimal b1 = BigDecimal.valueOf(saveTatal);
			BigDecimal b2 = BigDecimal.valueOf(cash.get(i).save());
			saveTatal = b1.add(b2).doubleValue();			
		}
		
		//打印买二赠一的信息
		boolean isTitlePrint = false;//是否已打印买二赠一标题 
		for(int i = 0;i < item.size(); i++ ){
			if (!isTitlePrint && cash.get(i).type == 2 || cash.get(i).type == 3){
				//打印买二赠一标题
				System.out.println("----------------------");
				System.out.println("买二赠一商品：");
				isTitlePrint = true;
			}
			if (isTitlePrint && cash.get(i).type == 2 || cash.get(i).type == 3){
				cash.get(i).printMoreInfo();//打印买二赠一商品信息  
			}
		}
		
		System.out.println("----------------------");
		System.out.println("总计：" + priceTatal + "(元)");
		
        //打印节省价格
		if(saveTatal!=0){
			System.out.println("节省：" + saveTatal + "(元)");
		}
	
		System.out.println("**********************");		
	}

	
}

