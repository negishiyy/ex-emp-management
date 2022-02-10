package jp.co.sample.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateEmployeeForm {
	
	private String id;
	
	@NotBlank(message="扶養人数が0人の場合でも「0」を入力してください")
	private String dependentsCount;
	
	
	public String getId() {
		return id;
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
