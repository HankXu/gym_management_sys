package com.xyz.gym_management_sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xyz.gym_management_sys.dao.RoleDao;
import com.xyz.gym_management_sys.po.Role;
import com.xyz.gym_management_sys.service.RoleService;
import com.xyz.gym_management_sys.vo.RoleVo;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Resource
	private Mapper mapper;
	
	private Role role;
	
	@Transactional
	public boolean updateRole(RoleVo roleVo) {
		role = new Role();
		
		role = mapper.map(roleVo, Role.class);
		
		roleDao.updateRole(role);
		return true;
	}

	public RoleVo findRoleById(RoleVo roleVo) {
		return null;
	}
	
	@Transactional
	public List<RoleVo> findRoles(RoleVo roleVo) {
		role = new Role();
		if(roleVo.getRoleId()!=0&&roleVo.getRoleName()!=null){
			role.setRoleId(roleVo.getRoleId());
			role.setRoleName(roleVo.getRoleName());
		}
		
		List<Role> roleList = roleDao.findRoleByNotNullProp(role);
		List<RoleVo> roleVoList = new ArrayList();
		
		
		for(int i = 0;i<roleList.size();i++){
			RoleVo tempVo = new RoleVo();
			tempVo = mapper.map(roleList.get(i), RoleVo.class);
			roleVoList.add(tempVo);
		}
		return roleVoList;
	}

}
