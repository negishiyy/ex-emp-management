package jp.co.sample.form;

public class UpdateEmployeeForm {
	
	private String id;
	private String dependentsCount;
	
	
	public int getId() {
		return Integer.parseInt(id);
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}
	
	

}
