package com.bootdo.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "redirect:/blog";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/photo_s.jpg");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_s.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		Subject subject = SecurityUtils.getSubject();
		UserDO user = (UserDO) subject.getPrincipal();
		if(null != user){
			return "redirect:/index";
		}
		return "login";
	}

	/*@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		UserDO user = (UserDO) subject.getPrincipal();
		System.out.println("username:"+user);
			try {
				//subject.login(token);
				return R.ok();
			} catch (AuthenticationException e) {
				return R.error("用户或密码错误");
			}
		
	}*/
	
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)    
    public String login(HttpServletRequest request,  HttpServletResponse response, Map<String, Object> map) {    
        System.out.println("loginController.login");    
        // 登录失败从request中获取shiro处理的异常信息    
        // shiroLoginFailure:就是shiro异常类的全类名    
        String exception = (String) request.getAttribute("shiroLoginFailure");    
        String msg = "";    
        if (exception != null) {    
            if (UnknownAccountException.class.getName().equals(exception)) {    
                System.out.println("UnknownAccountException -->帐号不存在：");    
                msg = "UnknownAccountException -->帐号不存在：";    
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {    
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");    
                msg = "IncorrectCredentialsException -- > 密码不正确：";    
            } else if ("kaptchaValidateFailed".equals(exception)) {    
                System.out.println("kaptchaValidateFailed -- > 验证码错误");    
                msg = "kaptchaValidateFailed -- > 验证码错误";    
            } else {    
                msg = "else >> " + exception;    
                System.out.println("else -- >" + exception);    
            }    
        }    
        Subject subject = SecurityUtils.getSubject();
		UserDO user = (UserDO) subject.getPrincipal();
		if(null != user){
			return "redirect:/index";
		}
        
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            out.println(JSON.toJSONString("{code:1,msg:'登录失败'}"));
            out.flush();
            out.close();
          return null;
    }    */
	

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

	@GetMapping("/403")
	String error403() {
		return "403";
	}

}
