package classprj.app.model;

public enum ROLEVALUE {
	STUDENT	("STUDENT",1L),
	TEACHER("TEACHER",2L);
	
	private String RoleName;
	private Long RoleId;
	
	private ROLEVALUE(String Value,Long id) {
		this.RoleName=Value;
		this.RoleId=id;
	}
	
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public Long getRoleId() {
		return RoleId;
	}
	public void setRoleId(Long roleId) {
		RoleId = roleId;
	}
	
}
