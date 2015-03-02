package iteratorTest;

public class DinerMenu {
	static final int MAX_ITEMS=1;
	int numberOfItems=0;
	MenuItem[] menuItems;
	
	public DinerMenu(){
		menuItems=new MenuItem[MAX_ITEMS];
		addItem("DinerMenu","test",true,3.55);
	}
	public void addItem(String name,String description,boolean vegeterian,double price){
		MenuItem menuItem=new MenuItem(name,description,vegeterian,price);
		if(numberOfItems>=MAX_ITEMS){
			System.out.println("Error");
		}
		else{
			menuItems[numberOfItems]=menuItem;
			numberOfItems=numberOfItems+1;
		}
	}
	public MenuItem[] getMenuItems(){
		return menuItems;
	}
}
