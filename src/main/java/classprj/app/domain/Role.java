package classprj.app.domain;

import classprj.app.model.ROLEVALUE;

import javax.persistence.*;

@Entity
@Table(name="Role",uniqueConstraints = {
		@UniqueConstraint(columnNames ="roleName")
})
public class Role{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="roleName",nullable = false)
	private String roleName;
	
	public Role() {
		
	}
	
	
	public Role(Long id, String roleName) {
		this.id = id;
		this.roleName = roleName.toUpperCase();
	}
	
	public Role(ROLEVALUE role) {
		this.roleName=role.getRoleName();
		this.id=role.getRoleId();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName.toUpperCase();
	}


	@Override
	public String toString() {
		return "Role="+ roleName ;
	}
	
	
	
}
