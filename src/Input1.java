
public class Input1 {
	private String cid;
	private Integer pst;
	private Integer pft;
	private Integer mile;
	private String cartype;
	public Input1(String cid, Integer pst, Integer pft, Integer mile, String cartype) {
		this.cid = cid;
		this.pst = pst;
		this.pft = pft;
		this.mile =mile;
		this.cartype = cartype;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Integer getPst() {
		return pst;
	}
	public void setPst(Integer pst) {
		this.pst = pst;
	}
	public Integer getPft() {
		return pft;
	}
	public void setPft(Integer pft) {
		this.pft = pft;
	}
	public Integer getMile() {
		return mile;
	}
	public void setMile(Integer mile) {
		this.mile = mile;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	

}
