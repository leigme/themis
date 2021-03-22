package me.leig.project.themis.container;

import org.kie.api.event.rule.AgendaEventListener;

import java.io.IOException;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public interface ThemisRuleContainer {

    boolean compile() throws IOException;

    void execute(Object ...object) throws IllegalAccessException, InstantiationException;

    void execute(AgendaEventListener agendaEventListener, Object ...obj) throws IllegalAccessException, InstantiationException;
}
