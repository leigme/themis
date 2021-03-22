package me.leig.project.themis.container;

import me.leig.project.themis.bean.ThemisRuleBean;
import org.kie.api.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public interface ThemisResource {

    List<Resource> ruleBean2Resources(ThemisRuleBean themisRuleBean) throws IOException;

}
