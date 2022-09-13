package ClassPrj.app.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ClassRoom")
public class ClassRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String className;
	@ManyToOne
	@JoinColumn(name="creator_id",nullable = false)
	private Teacher creator;
	
	@ManyToMany(mappedBy = "subscribedTo")
	private List<Student> members;

	

	public ClassRoom(Long id, String className, Teacher creator, List<Student> members) {
		super();
		this.id = id;
		this.className = className;
		this.creator = creator;
		this.members = members;
	}

	public Teacher getCreator() {
		return creator;
	}

	public void setCreator(Teacher creator) {
		this.creator = creator;
	}

	public List<Student> getMembers() {
		return members;
	}

	public void setMembers(List<Student> members) {
		this.members = members;
	}
	
	
}
