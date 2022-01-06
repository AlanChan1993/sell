package com.imooc.sell;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogerTest {
    //private final Logger logger = LoggerFactory.getLogger(LogerTest.class);//方法里加当前类，输出日志时就能找到对应的类

    @Test
    public  void test1(){
        String name="imooc";
        String password="123456";
        log.info("name: {},password: {}",name,password);

           log.info("info·······");
           log.debug("debug······");
           log.error("error······");
    }
}
