import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;


public class Greedy {
	public static ArrayList<Car> cars = new ArrayList<Car>();
	public static void main(String[] args) throws ParseException {
		ArrayList<Things> thingsList = new ArrayList<Things>();
		File input = new File("E:/EXCEL/2.xls");
		ArrayList<Input2> input2 = Readinput2.readexcel2(input);
		ArrayList<Input1> input1 = Readinput1.readexcel1(input);
		for(Input2 in : input2){
		
			switch (in.getType()) {
			case 4:
				for (Input1 user : input1) {
					if (user.getCartype().equals("3")) {
						int cost =(int)Math.ceil(user.getMile()/2.67);
						int duration =user.getPft()-user.getPst();
						if (duration>=cost) {
							Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid(),user.getCartype());
							thingsList.add(thing);
						}
					
					
					}
				}
				if (thingsList.size()!=0) {
					output(thingsList);
					for (Car carremove : cars) {
						for(Things t : carremove.getThingsList()){
						//	System.out.println(t.getName()+","+t.getCost());
						
							for (int i = 0; i < input1.size(); i++) {
								if (input1.get(i).getCid().equals(t.getName())) {
									input1.remove(i);
								}
							}
						}
					}
				}
	
				break;
			case 3:
				for (Input1 user : input1) {
					if (user.getCartype().equals("2")) {
				
						int cost =(int)Math.ceil(user.getMile()/2.2);
						int duration =user.getPft()-user.getPst();
						if (duration>=cost) {
							Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid(),user.getCartype());
							thingsList.add(thing);
						}
					//	Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid());
					//	thingsList.add(thing);
						
					}
				}
				if (thingsList.size()!=0) {
					output(thingsList);
					for (Car carremove : cars) {
						for(Things t : carremove.getThingsList()){
							for (int i = 0; i < input1.size(); i++) {
								if (input1.get(i).getCid().equals(t.getName())) {
									input1.remove(i);
								}
							}
						}
					}
				}
	
				break;
			case 2:
				for (Input1 user : input1) {
					if (user.getCartype().equals("1")) {
					
					//	int cost =(int)Math.floor(user.getMile()/1.2);
						int cost =(int)Math.ceil(user.getMile()/1.2);
						int duration =user.getPft()-user.getPst();
						if (duration>=cost) {
							Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid(),user.getCartype());
							thingsList.add(thing);
						}
					//	Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid());
					//	thingsList.add(thing);
						
					}
					else if (user.getCartype().equals("3")) {
						int cost =(int)Math.ceil(user.getMile()/1.42);
						int duration =user.getPft()-user.getPst();
						if (duration>=cost) {
							Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid(),user.getCartype());
							thingsList.add(thing);
						}
					
					}
				}
				if (thingsList.size()!=0) {
					output(thingsList);
					for (Car carremove : cars) {
						for(Things t : carremove.getThingsList()){
						
				//			System.out.println("11111");
							for (int i = 0; i < input1.size(); i++) {
								if (input1.get(i).getCid().equals(t.getName())) {
									input1.remove(i);
								}
							}
						}
					}
				}
		
			
				break;
			case 1:
				for (Input1 user : input1) {
					if (user.getCartype().equals("1")) {
					//	Things thing1 = new Things(100, 360,20 ,"p1");
						int cost =(int)Math.ceil(user.getMile()/0.37);
						int duration =user.getPft()-user.getPst();
						if (duration>=cost) {
							System.out.println(cost);
							Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid(),user.getCartype());
							thingsList.add(thing);
						}
			
						
					}
					else if (user.getCartype().equals("2")) {
						int cost =(int)Math.ceil(user.getMile()/0.4);
						int duration =user.getPft()-user.getPst();
						if (duration>=cost) {
							Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid(),user.getCartype());
							thingsList.add(thing);
						}
					//	Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid());
					//	thingsList.add(thing);
					}
					else if (user.getCartype().equals("3")) {
						int cost =(int)Math.ceil(user.getMile()/0.42);
						int duration =user.getPft()-user.getPst();
						if (duration>=cost) {
							Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid(),user.getCartype());
							thingsList.add(thing);
						}
					//	Things thing = new Things(user.getPst(), user.getPft(), cost, user.getCid());
					//	thingsList.add(thing);
					}
				}
				if (thingsList.size()!=0) {
					output(thingsList);
					for (Car carremove : cars) {
						for(Things t : carremove.getThingsList()){
						

							for (int i = 0; i < input1.size(); i++) {
								if (input1.get(i).getCid().equals(t.getName())) {
									input1.remove(i);
								}
							}
						}
					}
				}
		
				break;

			default:
				break;
			}
		}
		int i=1;
	
		for(Car car: cars){
			int j=0;
			System.out.println("第"+i+"个charging point can charge：");
			for(Things t : car.getThingsList()){
				
				System.out.println(t.getName()+","+t.getCost()+"   "+t.getEVtype());
				j++;
			}
			System.out.println(j+"辆车");
		//	System.out.println("\n");
		i++;
		}
		System.out.println("\n 最少需要"+cars.size()+"辆车");
		Writefile.writefile(cars, input2);

		
	}
	public static void output(ArrayList<Things> thingsList) {
		
	//	System.out.println("排序前  ");
	//	for(Things t: thingsList){
	//		System.out.print(t.getStartTime()+"  "+t.getEndTIme()+"\n");
	//	}

		Sort.SortByendTime(thingsList, 0, thingsList.size());
	//	System.out.print("\n排序后\n");
//		for(Things t: thingsList){
	//		System.out.print(t.getStartTime()+" "+t.getEndTIme()+"\n");
	//	}
		GreedySelect(thingsList);
	//	System.out.println("\n 最少需要"+cars.size()+"辆车");
		
	}
	public static  void GreedySelect(ArrayList<Things> thingsList) {
	//	ArrayList<Integer> realstarttime = new ArrayList<Integer>();
		Car car = new Car();
		Integer middleTIme = thingsList.get(0).getStartTime()+thingsList.get(0).getCost();
	//	realstart..add(middleTIme);
 
		car.addThing(thingsList.get(0));
		thingsList.remove(0);
		cars.add(car);
		for(Iterator<Things> t = thingsList.iterator();t.hasNext();){
			Things tt = t.next();
			if ((tt.getEndTIme()-tt.getStartTime())<tt.getCost()) {
				break;
			}
			if (middleTIme<= tt.getStartTime()) {
				middleTIme = tt.getStartTime()+tt.getCost();
		
		
				car.addThing(tt);
				t.remove();
			}
			else if(middleTIme>tt.getStartTime()&&middleTIme+tt.getCost()<=tt.getEndTIme())
			{
				middleTIme = middleTIme + tt.getCost();
		
				car.addThing(tt);
				t.remove();
			}
			else {
				t.remove();
			}
		}

	
	}

}
