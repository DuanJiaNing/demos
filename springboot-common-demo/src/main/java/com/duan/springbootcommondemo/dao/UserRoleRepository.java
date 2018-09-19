package com.duan.springbootcommondemo.dao;

import com.duan.springbootcommondemo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created on 2018/9/12.
 *
 * @author DuanJiaNing
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findAllByUId(Integer uId);

}
