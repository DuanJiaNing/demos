package com.duan.springdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/30.
 *
 * @author DuanJiaNing
 */
@Aspect
@Component
public class EncoreableIntroducer {

    @DeclareParents(value = "com.duan.springdemo.aspect.Performance+", defaultImpl = DefaultEncoreable.class)
    private static Encoreable encoreable;

}
