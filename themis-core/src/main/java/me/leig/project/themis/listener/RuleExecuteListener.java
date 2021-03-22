package me.leig.project.themis.listener;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.event.rule.AfterMatchFiredEvent;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public abstract class RuleExecuteListener extends DefaultAgendaEventListener {

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        super.afterMatchFired(event);
        if (null != event.getMatch() && null != event.getMatch().getRule()) {
            hitRuleName(event.getMatch().getRule().getName());
        }
    }

    public abstract void hitRuleName(String ruleName);

    public abstract void hitRuleCount(int result);
}
