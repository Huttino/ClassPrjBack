package classprj.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NewClassRoomRequest {

	@NotNull
	@NotEmpty
	private String className;

	private List<Long> scopesId;
	@NotNull
	@NotEmpty
	private String description;
	
	public NewClassRoomRequest() {
	}

	public NewClassRoomRequest(String className, List<Long> scopesId,String description) {
		this.className = className;
		this.scopesId = scopesId;
		this.description=description;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getScopesId() {
		return scopesId;
	}

	public void setScopesId(List<Long> scopesId) {
		this.scopesId = scopesId;
	}
}
