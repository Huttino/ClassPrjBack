package ClassPrj.app.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="ClassRoom",uniqueConstraints = {
		@UniqueConstraint(columnNames = "name")
})
public class ClassRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String className;
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn( name="creator_id",nullable = false)
	private Teacher creator;
	
	@ManyToMany(cascade=CascadeType.DETACH, mappedBy = "subscribedTo")
	private List<Student> members;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="uploadedTo")
	private List<Document> documents;

	

	public ClassRoom() {
	}

	public ClassRoom(Long id, String className, Teacher creator, List<Student> members) {
		super();
		this.id = id;
		this.className = className;
		this.creator = creator;
		this.members = members;
	}

	
	
	public ClassRoom(String className) {
		this.className = className;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassRoom other = (ClassRoom) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
