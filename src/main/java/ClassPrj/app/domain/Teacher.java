package ClassPrj.app.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Teacher")
public class Teacher extends User {

	
	@OneToMany(mappedBy = "creator")
	List<ClassRoom> hasCreated;

	public Teacher(Long id, String username, String password, String firstName, String lastName, List<Role> roles,
			List<ClassRoom> hasCreated) {
		super(id, username, password, firstName, lastName, roles);
		this.hasCreated = hasCreated;
	}

	public List<ClassRoom> getHasCreated() {
		return hasCreated;
	}

	public void setHasCreated(List<ClassRoom> hasCreated) {
		this.hasCreated = hasCreated;
	}
	
	
	
}
