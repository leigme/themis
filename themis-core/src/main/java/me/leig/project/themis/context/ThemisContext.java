package me.leig.project.themis.context;

import me.leig.project.themis.bean.ThemisRuleBean;
import me.leig.project.themis.container.ThemisRuleContainer;

import java.io.IOException;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public interface ThemisContext {

    void addContainer(ThemisRuleBean themisRuleBean) throws IOException;

    ThemisRuleContainer getContainer(String ruleName);

    void execute(String ruleName, Object ...objects) throws InstantiationException, IllegalAccessException;

    void delContainer(String ruleName);
}
