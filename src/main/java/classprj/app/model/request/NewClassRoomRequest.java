package classprj.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NewClassRoomRequest {

	@NotNull
	@NotEmpty
	private String className;

	@NotNull
	private List<Long> scopesId;
	
	public NewClassRoomRequest() {
	}

	public NewClassRoomRequest(String className, List<Long> scopesId) {
		this.className = className;
		this.scopesId = scopesId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Long> getScopesId() {
		return scopesId;
	}

	public void setScopesId(List<Long> scopesId) {
		this.scopesId = scopesId;
	}
}
