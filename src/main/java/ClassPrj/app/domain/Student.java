package ClassPrj.app.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student extends User {
	
	@ManyToMany
	@JoinTable(
			name = "student_classRoom",
			joinColumns = @JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="classRoom_id"))
	List<ClassRoom> subscribedTo;

	public Student(Long id, String username, String password, String firstName, String lastName, List<Role> roles,
			List<ClassRoom> subscribedTo) {
		super(id, username, password, firstName, lastName, roles);
		this.subscribedTo = subscribedTo;
	}

	public List<ClassRoom> getSubscribedTo() {
		return subscribedTo;
	}

	public void setSubscribedTo(List<ClassRoom> subscribedTo) {
		this.subscribedTo = subscribedTo;
	}

	
	
	
}
