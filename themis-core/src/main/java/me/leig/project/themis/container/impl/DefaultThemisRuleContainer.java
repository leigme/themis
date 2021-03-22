package me.leig.project.themis.container.impl;

import me.leig.project.themis.bean.ThemisRuleBean;
import me.leig.project.themis.container.ThemisRuleContainer;
import me.leig.project.themis.listener.RuleExecuteListener;
import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public class DefaultThemisRuleContainer implements ThemisRuleContainer {

    private final KnowledgeBuilder knowledgeBuilder;

    private final InternalKnowledgeBase internalKnowledgeBase;

    private final ThemisRuleBean themisRuleBean;

    public DefaultThemisRuleContainer(KnowledgeBuilder knowledgeBuilder, InternalKnowledgeBase internalKnowledgeBase, ThemisRuleBean themisRuleBean) {
        this.knowledgeBuilder = knowledgeBuilder;
        this.internalKnowledgeBase = internalKnowledgeBase;
        this.themisRuleBean = themisRuleBean;
    }

    @Override
    public boolean compile() throws IOException {
        List<Resource> resourceList = ThemisResourceImpl.newInstance().ruleBean2Resources(themisRuleBean);
        for (Resource resource: resourceList) {
            knowledgeBuilder.add(resource, ResourceType.DRL);
        }
        if (!CollectionUtils.isEmpty(knowledgeBuilder.getErrors())) {
            System.out.println(knowledgeBuilder.getErrors());
            return false;
        }
        internalKnowledgeBase.addPackages(knowledgeBuilder.getKnowledgePackages());
        return true;
    }

    @Override
    public void execute(Object ...objects) throws IllegalAccessException, InstantiationException {
        RuleExecuteListener ruleExecuteListener = themisRuleBean.getRuleExecuteListener();
        if (null == ruleExecuteListener) {
            ruleExecuteListener = new RuleExecuteListener() {
                @Override
                public void hitRuleName(String ruleName) {
                    System.out.printf("hit rule name is %s%n", ruleName);
                }

                @Override
                public void hitRuleCount(int result) {
                    System.out.printf("hit rule count is %s%n", result);
                }
            };
        }
        this.execute(ruleExecuteListener, objects);
    }

    @Override
    public void execute(AgendaEventListener agendaEventListener, Object ...obj) throws IllegalAccessException, InstantiationException {
        KieSession kieSession = internalKnowledgeBase.newKieSession();
        kieSession.addEventListener(agendaEventListener);
        for (int i = 0; i < obj.length; i++) {
            Object o = obj[i].getClass().newInstance();
            BeanUtils.copyProperties(obj[i], o);
            kieSession.insert(o);
            obj[i] = o;
        }
        int result = kieSession.fireAllRules();
        if (null != themisRuleBean.getRuleExecuteListener()) {
            themisRuleBean.getRuleExecuteListener().hitRuleCount(result);
        }
        kieSession.dispose();
    }
}
