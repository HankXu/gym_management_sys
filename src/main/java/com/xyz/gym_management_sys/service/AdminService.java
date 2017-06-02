package com.xyz.gym_management_sys.service;

import com.xyz.gym_management_sys.vo.AdminVo;
import com.xyz.gym_management_sys.vo.LoginVo;

/**
 * 这个接口定义了管理员用户相关的方法，如登陆、添加新管理员等
 * @author HIPAA
 */
public interface AdminService {
	
	/**
	 * 添加一个新的管理员账户的业务方法
	 * @param adminVo
	 * @return String
	 */
	public String addNewAdmin(AdminVo adminVo);
	
	/**
	 * 删除一个已有的管理员账户的业务方法,传入的VO类中应该包含：用户ID，用户名，用户角色名
	 * @param adminVo
	 * @return String
	 */
	public String deleteAdmin(AdminVo adminVo);
	
	/**
	 * 管理员账户登陆,返回的字符串为各种情况的代码
	 * @param adminVo
	 * @return AdminVo
	 */
	public AdminVo adminLogin(LoginVo loginVo);
	
	/**
	 * 管理员账户登出
	 * @param adminVo
	 * @return boolean
	 */
	public boolean adminLogout(AdminVo adminVo);
}
