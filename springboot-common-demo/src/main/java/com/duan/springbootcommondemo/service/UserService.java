package com.duan.springbootcommondemo.service;

import com.duan.common.base.restful.ResultModel;
import com.duan.springbootcommondemo.dao.UserRepository;
import com.duan.springbootcommondemo.entity.User;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2018/9/17.
 *
 * @author DuanJiaNing
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findList(int pageNum, int pageSize) {
        return userRepository.findAll(new PageRequest(pageNum, pageSize));
    }

    @Transactional
    public ResultModel<Integer> insertPerson(User user) {
        User save = userRepository.save(user);
        return new ResultModel<>(save.getId());
    }

    // -----------------------------------

    @Transactional(rollbackFor = IllegalArgumentException.class)
    public ResultModel<Integer> testInsertPerson(User user) {

        User save = userRepository.save(user);

        if (StringUtils.isEmptyOrWhitespaceOnly(user.getName())) {
            throw new IllegalArgumentException();
        }

        if (save != null && save.getId() != null) {
            return new ResultModel<>(save.getId());
        }

        return new ResultModel<>("error");
    }

    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public void testPerson(User user) {

        User save = userRepository.save(user);

        if (StringUtils.isEmptyOrWhitespaceOnly(user.getName())) {
            throw new IllegalArgumentException();
        }

    }
    // 无论如何方法都会调用，添加 person 到缓存，key 为参数 id

    @CachePut(value = "person", key = "#id")
    public User testGetPerson(Integer id) {
        System.out.println("testGetPerson for cache test");
        return userRepository.findOne(id);
    }
    // 删除缓存 person，key 为参数 id

    @CacheEvict(value = "person", key = "#id")
    public void testRemovePerson(Integer id) {
        System.out.println("testRemovePerson for cache test");
        userRepository.delete(id);
    }
    // 缓存中存在时直接返回，不调用方法，否则调用方法并放到缓存中

    @Cacheable(value = "person", key = "#id")
    public User testGetPersonIfNeed(Integer id) {
        System.out.println("testGetPersonIfNeed for cache test");
        return userRepository.findOne(id);
    }
}
