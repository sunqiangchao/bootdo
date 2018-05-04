package com.bootdo.common.service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Test;

/**
 * 测试表
 * 
 * @author sunqc
 */
public interface TestService {
	
	Test get(Long id);
	
	List<Test> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Test test);
	
	int update(Test test);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
