package com.duan.springbootdemo.repository;

import com.duan.springbootdemo.domain.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2018/6/6.
 *
 * @author DuanJiaNing
 */
@Repository
public interface OrgRepository extends JpaRepository<Org,Integer> {

    List<Org> findByAge(Integer number);

}
