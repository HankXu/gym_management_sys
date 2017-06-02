package com.xyz.gym_management_sys.vo;

/**
 * 这是用于与前台进行业务交互的管理员账户实体类
 * @author HIPAA
 */
public class AdminVo {
	private int adminId;
	private String adminName;
	private String relName;
	private String roleName;
	
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getRelName() {
		return relName;
	}
	public void setRelName(String relName) {
		this.relName = relName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
