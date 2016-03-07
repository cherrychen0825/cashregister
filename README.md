
思路：

策略模式+工厂模式 方便修改活动方案



文件说明：

1.代码类

Test.java           测试类

CashRegister.java   收银机，提取测试数据，打印小票

Cash.java           工厂类：判断商品类型，生成相应收费类

CashSuper.java      抽象类

CashDiscount.java   打折类

CashFreeOffer.java  买二赠一类

CashNormal.java     不做活动类

2.数据类

test.txt            测试json数据

itemInfo.txt        超市所有商品信息：条形码，名称，单价，计量单位

freeOfferItem.txt   买二赠一商品信息：条形码，名称

discountItem.txt    打折商品信息：条形码，名称
