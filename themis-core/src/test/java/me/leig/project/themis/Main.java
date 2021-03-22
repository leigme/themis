package me.leig.project.themis;

import com.google.common.io.Files;
import org.junit.Test;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public class Main {

    @Test
    public void testMain() {
        String ext = Files.getFileExtension("/Users/leig/Developer/Git/themis/20210314/themis/themis-core/src/test/resources/Message.drl");
        System.out.println(ext);
    }

}
