package com.xyz.gym_management_sys.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.gym_management_sys.service.AdminService;
import com.xyz.gym_management_sys.vo.AdminVo;
import com.xyz.gym_management_sys.vo.LoginVo;



@Controller
@RequestMapping("/login")
public class AdminLoginController {

	@Resource
	private AdminService adminService;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/adminLogin")
	public String Login(){
		return "adminLogin";
	}
	
	@RequestMapping(value = "/doLogin" ,method=RequestMethod.POST)
	public String DoLogin(@ModelAttribute LoginVo loginVo,Model model){
		AdminVo currUser = new AdminVo();
		
		if(request.getSession().getAttribute("admin")!=null){
			//如果session中已经存在一个管理员账户，移除它
			request.getSession().removeAttribute("admin");
		}

		try {
			currUser=adminService.adminLogin(loginVo);  //登陆
			
			if(currUser.getAdminName() == null){
				//为空，则表示用户名或密码错误，返回管理员登录页
				String wrongMsg = "用户名或密码错误";
				model.addAttribute("wrongMsg", wrongMsg);
				
				return "adminLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();			
			//出现异常，登陆失败，返回登录页
			return "adminLogin";
		}
		
		//登陆成功
		request.getSession().setAttribute("admin", currUser);
		model.addAttribute("admin", currUser);
		
		return "index";
	}
	
	@RequestMapping("/logout")
	public String Logout(){
		//将session中的user对象删除
		if(request.getSession().getAttribute("user")!=null){
			request.getSession().removeAttribute("user");
		}
		
		//返回登陆页面
		return "login";
	}

}
