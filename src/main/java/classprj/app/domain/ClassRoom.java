package classprj.app.domain;

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

	@Column(name = "name",nullable = false)
	private String className;
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn( name="creator_id",nullable = false)
	private Teacher creator;
	
	@OneToMany(cascade=CascadeType.DETACH, mappedBy = "classRoom")
	private List<Member> members;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="uploadedTo")
	private List<Document> documents;

	

	public ClassRoom() {
	}

	public ClassRoom(Long id, String className, Teacher creator, List<Member> members) {
		super();
		this.id = id;
		this.className = className.substring(0,1).toUpperCase()+className.substring(1);
		this.creator = creator;
		this.members = members;
	}

	
	
	public ClassRoom(String className) {
		this.className = className.substring(0,1).toUpperCase()+className.substring(1);
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
		this.className = className.substring(0,1).toUpperCase()+className.substring(1);
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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
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
