package iteratorTest;

import java.util.ArrayList;

public class PancakeHouseMenu {
	ArrayList<MenuItem> newItems;
	
	public PancakeHouseMenu(){
		newItems=new ArrayList<MenuItem>();
		addItem("PancakeHouseMenu","test",true,2.95);
	}
	public void addItem(String name,String description,boolean vegeterian,double price){
		MenuItem newItem=new MenuItem(name,description,vegeterian,price);
		newItems.add(newItem);
	}
	public ArrayList<MenuItem> getMenuItems(){
		return newItems;
	}
	
}
