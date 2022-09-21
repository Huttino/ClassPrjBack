package ClassPrj.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ClassPrj.app.Model.ROLEVALUE;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="Role",uniqueConstraints = {
		@UniqueConstraint(columnNames ="roleName")
})
public class Role{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="roleName")
	private String roleName;
	
	public Role() {
		
	}
	
	
	public Role(Long id, String roleName) {
		this.id = id;
		this.roleName = roleName;
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
		this.roleName = roleName;
	}


	@Override
	public String toString() {
		return "Role="+ roleName ;
	}
	
	
	
}
