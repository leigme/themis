package me.leig.project.themis.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/22
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testMain() {
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }
}
