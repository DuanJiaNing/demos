package com.duan.springbootdemo.verify;

/**
 * Created on 2018/9/14.
 *
 * @author DuanJiaNing
 */
public enum VerifyValueRule {

    /*
     * VALUE_: 基本数据类型
     * TEXT_: 字符型
     * COLLECTION_: 集合或数组
     */

    /**
     * 无作为，不做校验
     */
    NON,

    /**
     * ==
     */
    VALUE_EQUAL,

    /**
     * >
     */
    VALUE_GREATER_THAN,

    /**
     * <=
     */
    VALUE_NOT_GREATER_THAN,

    /**
     * <
     */
    VALUE_LESS_THAN,

    /**
     * >=
     */
    VALUE_NOT_LESS_THAN,

    /**
     * regex
     */
    TEXT_REGEX,

    /**
     * length == ？
     */
    TEXT_LENGTH_EQUAL,

    /**
     * length > ?
     */
    TEXT_LENGTH_GREATER_THAN,

    /**
     * length <= ?
     */
    TEXT_LENGTH_NOT_GREATER_THAN,

    /**
     * length < ?
     */
    TEXT_LENGTH_LESS_THAN,

    /**
     * length >= ?
     */
    TEXT_LENGTH_NOT_LESS_THAN,

    /**
     * size > ?
     */
    COLLECTION_SIZE_GREATER_THAN,

    /**
     * size <= ?
     */
    COLLECTION_SIZE_NOT_GREATER_THAN,

    /**
     * size < ?
     */
    COLLECTION_SIZE_LESS_THAN,

    /**
     * size >= ?
     */
    COLLECTION_SIZE_NOT_LESS_THAN,

    /**
     * size == ?
     */
    COLLECTION_SIZE_EQUAL

}
