import java.util.ArrayList;


public class Car {
	private ArrayList<Things> thingsList = new ArrayList<Things>();
	public ArrayList<Things> getThingsList(){
		return thingsList;
	}
	
	public void setThingsList(ArrayList<Things> thingsList) {
		this.thingsList = thingsList;
		
	}
	public Integer addThing(Things thing)
	{
		thingsList.add(thing);
		return thingsList.size();
		
	}

}
