package com.duan.springdemo.mvc.pad;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/12.
 *
 * @author DuanJiaNing
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Notepad {
}
