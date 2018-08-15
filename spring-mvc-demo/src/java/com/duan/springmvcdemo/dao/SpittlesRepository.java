package com.duan.springmvcdemo.dao;

import com.duan.springmvcdemo.entity.Spittle;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2018/8/15.
 *
 * @author DuanJiaNing
 */
@Repository
public interface SpittlesRepository {

    List<Spittle> findSpittles(long max, int count);

}
