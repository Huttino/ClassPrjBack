package ClassPrj.app.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Student")
public class Student extends User {
	
	@ManyToMany
	@JoinTable(
			name = "student_classRoom",
			joinColumns = @JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="classRoom_id"))
	private List<ClassRoom> subscribedTo;

	public Student() {
		super();
	}
	
	public Student(Long id, String username, String password, String firstName, String lastName, Role role,
			List<ClassRoom> subscribedTo) {
		super(id, username, password, firstName, lastName, role);
		this.subscribedTo = subscribedTo;
	}

	public List<ClassRoom> getSubscribedTo() {
		return subscribedTo;
	}

	public void setSubscribedTo(List<ClassRoom> subscribedTo) {
		this.subscribedTo = subscribedTo;
	}

	
	
	
}
