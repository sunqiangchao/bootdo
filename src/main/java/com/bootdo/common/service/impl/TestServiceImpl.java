package com.bootdo.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.common.dao.TestDao;
import com.bootdo.common.domain.Test;
import com.bootdo.common.service.TestService;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public Test get(Long id) {
        return testDao.get(id);
    }

    @Override
    public List<Test> list(Map<String, Object> map) {
        return testDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return testDao.count(map);
    }

    @Override
    public int save(Test test) {
        return testDao.save(test);
    }

    @Override
    public int update(Test test) {
        return testDao.update(test);
    }

    @Override
    public int remove(Long id) {
        return testDao.remove(id);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return testDao.batchRemove(ids);
    }
}
