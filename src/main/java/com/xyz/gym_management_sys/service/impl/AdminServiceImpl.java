package com.xyz.gym_management_sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.gym_management_sys.dao.AdminDao;
import com.xyz.gym_management_sys.dao.RoleDao;
import com.xyz.gym_management_sys.po.Admin;
import com.xyz.gym_management_sys.po.Role;
import com.xyz.gym_management_sys.service.AdminService;
import com.xyz.gym_management_sys.vo.AdminVo;
import com.xyz.gym_management_sys.vo.LoginVo;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDao adminDao;
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private Mapper mapper;

	@Transactional
	public String addNewAdmin(AdminVo adminVo) {
		Admin admin = new Admin();
		admin.setAdminName(adminVo.getAdminName());
		
		if(adminDao.findAdminByNotNullProp(admin).size()!=0){
			//已有相同用户名的管理员账户，添加失败。
			return "此用户名已存在";
		}
		
		admin = mapper.map(adminVo, Admin.class);  //将VO类映射为PO实体
		
		
		List<Role> roleList = roleDao.findRoleByNotNullProp(admin.getRole());
		
		if(roleList.size() == 0){
			//不存在这种角色类型
			return "所属角色类型不存在";
		}
		
		admin.setRole(roleList.get(0));  //重新设置带有roleId的角色对象到管理员对象中，以用于存储
		
		adminDao.addAdmin(admin);  //添加这个新的管理员账户到数据库
		
		return "添加管理员用户成功";
	}

	@Transactional
	public String deleteAdmin(AdminVo adminVo) {
		//删除的实现方案：通过传入一个adminVo实体，其admin用户名不为空，查找此用户名对应的数据对象，并将此对象作为删除方法的模板对象
		//选择这个方案的原因：使用这种方式可以进行用户存在与否的判断，同时不会造成在同一个Session中，存在两个相同主键的对象。
		//因为使用映射时又作用户存在判断时，需要使用用户名查找是否存在用户，这样会在session中留有一个用户对象。
		//如果这时自己又用业务实体中映射了一个带有主键的PO实体，就会造成在session中存在两个主键相同的对象。这样的情况是spring不允许存在的。
		Admin admin = new Admin();

		admin.setAdminName(adminVo.getAdminName());  //不使用ID，因为主键不能作为模板对象查找方式的条件
		
		List<Admin> adminList = adminDao.findAdminByNotNullProp(admin);
		if(adminList.size() == 0){
			//数据库不存在这个用户
			return "不存在此用户";
		}
		
		admin = adminList.get(0);
		
		adminDao.deleteAdmin(admin);  //删除这个管理员账户，删除时实体中应该有主键信息
		
		return "删除用户成功";
	}

	@Transactional
	public AdminVo adminLogin(LoginVo loginVo) {
		Admin admin = new Admin();
		
		admin = mapper.map(loginVo,Admin.class);
		
		List<Admin> adminResult = adminDao.findAdminByNotNullProp(admin);
		
		if(adminResult.size() == 0){
			//用户名+密码无法获取用户，返回一个空的VO类，说明用户名或密码错误
			return new AdminVo();
		}
		
		AdminVo resultVo = new AdminVo();
		
		resultVo = mapper.map(adminResult.get(0), AdminVo.class);  //将结果集中的第一个对象映射为VO实体
		
		return resultVo;  //返回的VO将被存放在Session中
	}

	public boolean adminLogout(AdminVo adminVo) {
		// TODO Auto-generated method stub
		return false;
	}

}
