package classprj.app.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

	@Column(name="cover",nullable = true)
	private String pathCover;

	@Column(name="description",nullable = false)
	private String description;

	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn( name="creator_id",nullable = false)
	private Teacher creator;
	
	@OneToMany(cascade=CascadeType.DETACH, mappedBy = "classRoom")
	private List<Member> members;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="uploadedTo")
	private List<Document> documents;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "classRoom")
	private List<VideoLesson> lessons;

	@ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name="classRoomId"),inverseJoinColumns =@JoinColumn(name="scopeId"))
	Set<Scope> scopes;

	public ClassRoom() {
	}

	public ClassRoom(Long id, String className, String pathCover, String description, Teacher creator, List<Member> members, List<Document> documents, List<VideoLesson> lessons, Set<Scope> scopes) {
		this.id = id;
		this.className = className;
		this.pathCover = pathCover;
		this.description = description;
		this.creator = creator;
		this.members = members;
		this.documents = documents;
		this.lessons = lessons;
		this.scopes = scopes;
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

	public List<VideoLesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<VideoLesson> lessons) {
		this.lessons = lessons;
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

	public String getPathCover() {
		return pathCover;
	}

	public void setPathCover(String pathCover) {
		this.pathCover = pathCover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
