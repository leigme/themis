package me.leig.project.themis.container.impl;

import me.leig.project.themis.bean.Message;
import me.leig.project.themis.bean.ThemisFileBean;
import me.leig.project.themis.bean.ThemisRuleBean;
import me.leig.project.themis.configuration.ThemisConfiguration;
import me.leig.project.themis.container.ThemisRuleContainer;
import org.drools.core.impl.InternalKnowledgeBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.internal.builder.KnowledgeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ThemisConfiguration.class)
public class DefaultThemisRuleContainerTest {

    @Autowired
    private KnowledgeBuilder knowledgeBuilder;

    @Autowired
    private InternalKnowledgeBase internalKnowledgeBase;

    private Message message = new Message(1, "test rule hello");

    @Test
    public void testDefaultThemisRuleContainer() throws IOException, InstantiationException, IllegalAccessException {
        ThemisRuleBean themisRuleBean = new ThemisRuleBean();
        ThemisFileBean themisFileBean = new ThemisFileBean(new File("/Users/leig/Developer/Git/themis/20210314/themis/themis-core/src/test/resources/Message.drl"));
        themisRuleBean.addThemisFileBean(themisFileBean);
        ThemisRuleContainer themisRuleContainer = new DefaultThemisRuleContainer(knowledgeBuilder, internalKnowledgeBase, themisRuleBean);
        themisRuleContainer.compile();
        Object[] objects = new Object[] {message};
        themisRuleContainer.execute(objects);
        for (Object o: objects) {
            System.out.println(o);
        }
    }

}
