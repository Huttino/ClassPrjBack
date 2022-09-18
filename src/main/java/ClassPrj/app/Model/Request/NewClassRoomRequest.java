package ClassPrj.app.Model.Request;

public class NewClassRoomRequest {
	private String classname;
	
	public NewClassRoomRequest() {
	}

	public NewClassRoomRequest(String classname) {
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
