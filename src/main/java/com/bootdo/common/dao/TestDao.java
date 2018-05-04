package com.bootdo.common.dao;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 测试表
 * 
 * @author sunqc
 */
@Mapper
public interface TestDao {

	Test get(Long id);

	List<Test> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(Test test);

	int update(Test test);

	int remove(Long id);

	int batchRemove(Long[] ids);
}
