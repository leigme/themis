package me.leig.project.themis.bean;

import me.leig.project.themis.listener.RuleExecuteListener;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public class ThemisRuleBean {

    private String ruleName;

    private List<ThemisFileBean> themisFileBeans;

    private Map<String, Collection<Object>> collectionMap;

    private RuleExecuteListener ruleExecuteListener;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<ThemisFileBean> getThemisFileBeans() {
        return themisFileBeans;
    }

    public void setThemisFileBeans(List<ThemisFileBean> themisFileBeans) {
        this.themisFileBeans = themisFileBeans;
    }

    public void addThemisFileBean(ThemisFileBean themisFileBean) {
        if (CollectionUtils.isEmpty(themisFileBeans)) {
            themisFileBeans = new ArrayList<>();
        }
        themisFileBeans.add(themisFileBean);
    }

    public Map<String, Collection<Object>> getCollectionMap() {
        return collectionMap;
    }

    public void setCollectionMap(Map<String, Collection<Object>> collectionMap) {
        this.collectionMap = collectionMap;
    }

    public RuleExecuteListener getRuleExecuteListener() {
        return ruleExecuteListener;
    }

    public void setRuleExecuteListener(RuleExecuteListener ruleExecuteListener) {
        this.ruleExecuteListener = ruleExecuteListener;
    }
}
