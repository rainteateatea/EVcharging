
public class Things {
	private String name;
	private Integer startTime;
	private Integer endTime;
	private Integer cost;
	private String EVtype;
	
	public Things(Integer startTime, Integer endTime,Integer cost, String name,String EVtype) {
		this.endTime = endTime;
		this.startTime = startTime;
		this.cost = cost;
		this.name = name;
		this.EVtype = EVtype;
	}
	public String getEVtype() {
		return EVtype;
	}
	public void setEVtype(String eVtype) {
		EVtype = eVtype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStartTime(){
		return startTime;
	}
	public void setStartTime(Integer startTime){
		this.startTime = startTime;
	}
	public Integer getEndTIme(){
		return endTime;
	}
	public void setEndTime(Integer endTime){
		this.endTime = endTime;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost){
		this.cost = cost;
	}

}
