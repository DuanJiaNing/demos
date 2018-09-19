package com.duan.springbootcommondemo.dao;

import com.duan.springbootcommondemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2018/9/12.
 *
 * @author DuanJiaNing
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
