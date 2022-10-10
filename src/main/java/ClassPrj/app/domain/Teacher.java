package ClassPrj.app.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name="Teacher")
public class Teacher extends User {

	
	@OneToMany(mappedBy = "creator")
	private List<ClassRoom> hasCreated;
	
	@OneToMany(mappedBy = "uploadedBy")
	private List<Document> uploaded;	
	
	
	
	public Teacher() {
	}

	public Teacher(Long id, String username, String password, String firstName, String lastName, List<Role> roles,
			List<ClassRoom> hasCreated, List<Document> uploaded) {
		super(id, username, password, firstName, lastName, roles);
		this.hasCreated = hasCreated;
		this.uploaded = uploaded;
	}

	public List<Document> getUploaded() {
		return uploaded;
	}

	public void setUploaded(List<Document> uploaded) {
		this.uploaded = uploaded;
	}

	public List<ClassRoom> getHasCreated() {
		return hasCreated;
	}

	public void setHasCreated(List<ClassRoom> hasCreated) {
		this.hasCreated = hasCreated;
	}
	
	
	
}
