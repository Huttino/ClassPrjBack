package ClassPrj.app.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Student")
public class Student extends User {

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Member> classList;

	public Student() {
	}

	public Student(Long id, String username, String password, String firstName, String lastName, Role role, List<Member> classList) {
		super(id, username, password, firstName, lastName, role);
		this.classList = classList;
	}

	public List<Member> getClassList() {
		return classList;
	}

	public void setClassList(List<Member> classList) {
		this.classList = classList;
	}
}
