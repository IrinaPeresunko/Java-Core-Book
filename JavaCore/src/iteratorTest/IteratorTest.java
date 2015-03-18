package iteratorTest;

import java.util.ArrayList;

public class IteratorTest {

	public static void main(String[] args) {
		PancakeHouseMenu phm= new PancakeHouseMenu();
		ArrayList<MenuItem> breakfastItems=phm.getMenuItems();
		DinerMenu dm=new DinerMenu();
		MenuItem[] lunchItems=dm.getMenuItems();
		
//		for(int i=0;i<breakfastItems.size();i++){
//			MenuItem menuItem=(MenuItem) breakfastItems.get(i);
//			System.out.println(menuItem.getName()+" ");
//		}
//		
//		for(int i=0;i<lunchItems.length;i++){
//			MenuItem menuItem=lunchItems[i];
//			System.out.println(menuItem.getName()+" ");
//		}
		
		Iterator it=new Iterator(lunchItems,breakfastItems);
		
		while((it.hasNext() && it.hasNextArrayList())==true){
			MenuItem item=it.next();
			System.out.println(item.getName()+" - price: "+item.getPrice());
			
			item=it.nextArrayList();
			System.out.println(item.getName()+" - price: "+item.getPrice());
		}
	}
}
