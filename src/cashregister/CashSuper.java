package cashregister;
/**

* 类的描述

* @author Chen Jun

* @Time 2016-03-02

*

*/


public abstract class CashSuper {	
	public String itemName;//商品名称
	public int itemNum;//商品购买数量
	public String unit;//商品计量单位
	public double unitPrice;//商品单价
	
	public abstract double acceptCash();
	public abstract void printInfo();
	public abstract void printMoreInfo();
	public abstract double save();
}
