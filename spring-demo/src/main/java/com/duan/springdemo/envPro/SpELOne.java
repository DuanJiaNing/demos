package com.duan.springdemo.envPro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created on 2018/7/19.
 *
 * @author DuanJiaNing
 */
@Slf4j
@Component
public class SpELOne implements BeanNameAware {

    // java 类属性、方法调用-----------------------------------------

    @Value("#{T(System).currentTimeMillis()}")
    private Long currentMillis;

    @Value("#{T(java.lang.Math).random()}")
    private double random;

    // bean 属性、方法调用-----------------------------------------

    @Value("#{envOne.version}")
    private Double version;

    @Value("#{envOne.getClass().toString().toUpperCase()}")
    private String className;

    @Value("#{envOne.getStr(true)?.toUpperCase()}") // envOne.getStr(true) 返回 null 就不调用 toUpperCase 方法
    private String nullableStr;

    @Value("#{systemProperties['jnidispatch.path']}")
    private String jnidispatch;

    // 字面值-----------------------------------------

    @Value("#{3.1415926}")
    private float pi;

    @Value("#{'Hello'}")
    private String hello;

    @Value("#{false}")
    private boolean cons;

    @Value("#{9.87E4}")
    private long num;

    // 运算符-----------------------------------------

    @Value("#{2 * T(Math).PI * envOne.version}")
    private double res;

    @Value("#{'hello' + 'world' + T(Math).PI + envOne.getStr(false)}")
    private String strAd;

    @Value("#{100 >= T(Math).random()}")
    private boolean com;

    @Value("#{100 eq T(Math).random()}") // 文本 eq 代替 ==
    private boolean comEq;

    @Value("#{envOne.getStr(false) == null ? 'null' : 'not null'}")
    private String nullW;

    @Value("#{envOne.getStr(false) ?: 'not null'}") // 为 null 用默认字符串替代，否则返回值
    private String nullS;

    // 正则表达式-----------------------------------------

    // @Value("#{'22@qq.com'.matches('[a-zA-z0-9@\\w.*?\\.\\w.*?]')}")
    @Value("#{'22@qq.com' matches '[a-zA-z0-9@\\w.*?\\.\\w.*?]'}")
    private boolean match1;

    // 集合-----------------------------------------

    @Value("#{envOne.songs[0]}")
    private Song song;

    @Value("#{envOne.songs.?[artist eq 'artist b']}") // 取出 songs 集合中所有 artist 属性值为 'artist b' 的歌曲
    private Song[] artistBSongs;

    @Value("#{envOne.songs.^[artist eq 'artist b']}") // 取出 songs 集合中第一个 artist 属性值为 'artist b' 的歌曲
    private Song artistBSongsFirst;

    @Value("#{envOne.songs.$[artist eq 'artist b']}") // 取出 songs 集合中最后一个 artist 属性值为 'artist b' 的歌曲
    private Song artistBSongsLast;

    @Value("#{envOne.songs.![title]}") // 取出 songs 集合中所有的 title 属性，装配到新的集合中
    private String[] songTitles;

    @Value("#{envOne.songs.?[artist == 'artist b'].![title]}") // 组合使用
    private String[] artisBSongTitles;

    @Override
    public void setBeanName(String name) {
        a1();
        a2();
        a3();
        a4();
        a6();
        a5();
    }

    private void a1() {
        log.info("a1 " + currentMillis.toString());
        log.info("a1 " + random + "");
    }

    private void a2() {
        log.info("a2 " + version + "");
        log.info("a2 " + className);
        log.info("a2 " + nullableStr);
        log.info("a2 " + jnidispatch);
    }

    private void a3() {
        log.info("a3 " + pi + "");
        log.info("a3 " + hello + "");
        log.info("a3 " + cons + "");
        log.info("a3 " + num + "");
    }

    private void a4() {
        log.info("a4 " + res + "");
        log.info("a4 " + strAd);
        log.info("a4 " + com + "");
        log.info("a4 " + comEq + "");
        log.info("a4 " + nullW);
        log.info("a4 " + nullS);
    }

    private void a6() {
        log.info("a6 " + song.toString());
        log.info("a6 " + Arrays.toString(artistBSongs));
        log.info("a6 " + artistBSongsFirst.toString());
        log.info("a6 " + artistBSongsLast.toString());
        log.info("a6 " + Arrays.toString(songTitles));
        log.info("a6 " + Arrays.toString(artisBSongTitles));
    }

    private void a5() {
        log.info("a5 " + match1 + "");
    }
}
