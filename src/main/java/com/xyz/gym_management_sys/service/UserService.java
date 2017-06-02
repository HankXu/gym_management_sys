package com.xyz.gym_management_sys.service;

/**
 * 这个接口定义了用户相关的业务方法，包括登陆、登出、更改用户信息等
 * @author HIPAA
 */
public interface UserService {

	/**
	 * 注册用户的业务方法,返回的字符串是执行状态代码
	 * @param userVo
	 * @return String
	 */
	public String registerNewUser(UserVo userVo);
	
	/**
	 * 普通用户登录的业务方法，返回的字符串是执行状态代码
	 * @param userVo
	 * @return String
	 */
	public String userLogin(UserVo userVo);
	
	/**
	 * 普通用户登出的业务方法
	 * @param userVo
	 * @return
	 */
	public boolean userLogout(UserVo userVo);
	
	/**
	 * 普通用户修改用户信息的业务方法
	 * @param userInfoVo
	 * @return boolean
	 */
	public boolean userInfoChange(UserInfoVo userInfoVo);
}
