package com.bootdo.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.domain.Test;
import com.bootdo.common.service.TestService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 测试表
 * 
 * @author sunqc
 * @date 2018-1-21 18:28:07
 */

@Controller
@RequestMapping("/common/test")
public class TestController extends BaseController {
	@Autowired
	private TestService testService;

	@GetMapping()
	@RequiresPermissions("common:test:test")
	String sysDict() {
		return "common/test/test";
	}
	@Log("测试列表")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:test:test")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		System.out.println("!!!!!!!!!!!!!!!!!!");
		// 查询列表数据
		Query query = new Query(params);
		List<Test> testList = testService.list(query);
		int total = testService.count(query);
		PageUtils pageUtils = new PageUtils(testList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("common:test:add")
	String add() {
		return "common/test/add";
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:test:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		Test test = testService.get(id);
		model.addAttribute("test", test);
		return "common/test/edit";
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:test:add")
	public R save(Test test) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (testService.save(test) > 0) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 更新
	 */
	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("common:test:edit")
	public R update(Test test) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (testService.update(test) > 0) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("common:test:remove")
	public R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (testService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}
	@PostMapping(value = "/upload")  
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
  
        System.out.println("开始");  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println("~~~~~~~~~~~~~~~~~~filename:"+file.getOriginalFilename());
  
        return "redirect:/common/test";
    }  
	@ResponseBody
	@PostMapping(value = "/upload2")  
    public String upload2(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
  
        System.out.println("开始");  
        String fileName = file.getOriginalFilename();  
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println("~~~~~~~~~~~~~~~~~~filename:"+file.getOriginalFilename());
  
        return "0";
    }  
}
