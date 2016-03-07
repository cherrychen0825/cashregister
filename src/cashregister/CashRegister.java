package cashregister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CashRegister {
	ArrayList<String> itemList = new ArrayList<String>();//json���ݴ�������������������Ϣ
	ArrayList<String>     item = new ArrayList<String>();//������Ʒÿ����Ʒ�����루û���ظ���
	ArrayList<Integer> item_num = new ArrayList<Integer>();//������Ʒÿ����Ʒ������

	public CashRegister() throws IOException{
		//��ȡJSON����
		BufferedReader bf= new BufferedReader(new FileReader("test.txt"));
		String line= null;
		while(( line= bf.readLine()) != null){ 
			//���к�β�в�����
			if (line.equals("[") || line.equals("]"))continue;
			//��ȡ��Ʒ������������	
			itemList.add(line.substring(line.indexOf("input")+9,line.lastIndexOf("}")-1));
      
		}
		bf.close();
	}

	//��ȡ��Ʒ������ �빺������
	public void getItemList(){
		for(int i=0;i<itemList.size();i++){
			int num_temp=1;//ɨһ��������ʱ��������� Ĭ��Ϊ1��
			String item_now = itemList.get(i);
			//����-ʱ��ɨ��Ĺ������Ϊ-���������
			if(item_now.contains("-")){
				num_temp = Integer.parseInt(item_now.substring(item_now.indexOf("-")+1, item_now.length()));
			} 
			
			//�鿴����ɨ�������������ɨ���б��е��±ꡣ�����±����-1��
			int i_item = item.indexOf(item_now);			
			//������itemList[i]ûɨ���ʱ
			if(i_item == -1 ){
				item.add(item_now.substring(0, 10));
				item_num.add(num_temp);	
				continue;
			}
			//������itemList[i]ɨ���ʱ
			num_temp = item_num.get(i_item)+num_temp;
			item_num.set(i_item,num_temp);	
		}
	}	

	//��ӡСƱ
	public void printList() throws Exception{
		getItemList();//��ȡ��Ʒ������item[]�빺������item_num[]
		ArrayList<Cash> cash = new ArrayList<Cash>();		
		double saveTatal = 0;
		double priceTatal = 0;
		System.out.println("***<ûǮ׬�̵�>�����嵥***");
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
		
		//��ӡ�����һ����Ϣ
		boolean isTitlePrint = false;//�Ƿ��Ѵ�ӡ�����һ���� 
		for(int i = 0;i < item.size(); i++ ){
			if (!isTitlePrint && cash.get(i).type == 2 || cash.get(i).type == 3){
				//��ӡ�����һ����
				System.out.println("----------------------");
				System.out.println("�����һ��Ʒ��");
				isTitlePrint = true;
			}
			if (isTitlePrint && cash.get(i).type == 2 || cash.get(i).type == 3){
				cash.get(i).printMoreInfo();//��ӡ�����һ��Ʒ��Ϣ  
			}
		}
		
		System.out.println("----------------------");
		System.out.println("�ܼƣ�" + priceTatal + "(Ԫ)");
		
        //��ӡ��ʡ�۸�
		if(saveTatal!=0){
			System.out.println("��ʡ��" + saveTatal + "(Ԫ)");
		}
	
		System.out.println("**********************");		
	}

	
}

