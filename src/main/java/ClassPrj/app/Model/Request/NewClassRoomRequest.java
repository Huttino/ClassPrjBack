package ClassPrj.app.Model.Request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewClassRoomRequest {

	@NotNull
	@NotEmpty
	private String classname;
	
	public NewClassRoomRequest() {
	}

	public NewClassRoomRequest(
			String classname) {
		super();
		this.classname = classname;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	
}
