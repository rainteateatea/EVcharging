import java.util.ArrayList;


public class Sort {
	public static void SortByendTime(ArrayList<Things> thingsSorted, Integer start, Integer end) {
	
		for (int i = 0; i < end; i++) {
			for (int j = 1; j < end-i; j++) {
			
				if (thingsSorted.get(j-1).getEndTIme()>thingsSorted.get(j).getEndTIme()) {
					Things t = thingsSorted.get(j-1);
					thingsSorted.set(j-1, thingsSorted.get(j));
					thingsSorted.set(j, t);
				}
			
				else if (thingsSorted.get(j-1).getEndTIme()-thingsSorted.get(j).getEndTIme()==0) {
					int temp1 = 0;
					int temp2 = 0;
					temp1 = thingsSorted.get(j-1).getStartTime()+thingsSorted.get(j-1).getCost();
					temp2 = thingsSorted.get(j).getStartTime()+thingsSorted.get(j).getCost();
					if (temp1>temp2) {
						Things t = thingsSorted.get(j-1);
						thingsSorted.set(j-1, thingsSorted.get(j));
						thingsSorted.set(j, t);
					}
				
				}
			}
			
		}
		return;
		
	}

}

