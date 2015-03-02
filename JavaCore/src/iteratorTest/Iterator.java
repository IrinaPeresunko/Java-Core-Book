package iteratorTest;

import java.util.ArrayList;

public class Iterator {

	int position;
	int positionArrayList;
	MenuItem[] items;
	ArrayList<MenuItem> newItems;
	
	public Iterator(MenuItem[] items,ArrayList<MenuItem> newItems){
		this.items=items;
		this.newItems=newItems;
	}
	public boolean hasNext(){
		if(position>=items.length || items[position]==null){
			return false;
		}
		else return true;
	}
	public boolean hasNextArrayList(){
		if(positionArrayList>=newItems.size()){
			return false;
		}
		else return true;
	}
	public MenuItem next(){
		MenuItem mi=items[position];
		position=position+1;
		return mi;
	}
	public MenuItem nextArrayList(){
		MenuItem mi=newItems.get(positionArrayList);
		positionArrayList=positionArrayList+1;
		return mi;
	}
}
