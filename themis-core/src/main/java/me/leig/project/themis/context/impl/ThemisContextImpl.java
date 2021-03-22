package me.leig.project.themis.context.impl;

import me.leig.project.themis.bean.ThemisRuleBean;
import me.leig.project.themis.container.ThemisRuleContainer;
import me.leig.project.themis.container.impl.DefaultThemisRuleContainer;
import me.leig.project.themis.context.ThemisContext;
import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public class ThemisContextImpl implements ThemisContext {

    private final Map<String, ThemisRuleContainer> containerMap = new ConcurrentHashMap<>();

    @Autowired
    private KnowledgeBuilder knowledgeBuilder;

    @Autowired
    private InternalKnowledgeBase internalKnowledgeBase;

    @Override
    public void addContainer(ThemisRuleBean themisRuleBean) throws IOException {
        ThemisRuleContainer themisRuleContainer = new DefaultThemisRuleContainer(knowledgeBuilder, internalKnowledgeBase, themisRuleBean);
        if (themisRuleContainer.compile()) {
            containerMap.put(themisRuleBean.getRuleName(), themisRuleContainer);
        }
    }

    @Override
    public ThemisRuleContainer getContainer(String ruleName) {
        return containerMap.get(ruleName);
    }

    @Override
    public void execute(String ruleName, Object... objects) throws InstantiationException, IllegalAccessException {
        getContainer(ruleName).execute(objects);
    }

    @Override
    public void delContainer(String ruleName) {
        containerMap.remove(ruleName);
    }
}
