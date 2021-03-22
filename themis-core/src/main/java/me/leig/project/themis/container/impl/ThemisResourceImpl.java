package me.leig.project.themis.container.impl;

import com.google.common.base.Strings;
import me.leig.project.themis.bean.ThemisFileBean;
import me.leig.project.themis.bean.ThemisFileType;
import me.leig.project.themis.bean.ThemisRuleBean;
import me.leig.project.themis.container.ThemisResource;
import me.leig.project.themis.exception.ThemisException;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.io.Resource;
import org.kie.internal.io.ResourceFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public enum ThemisResourceImpl implements ThemisResource {

    INSTANCE;

    public static ThemisResource newInstance() {
        return INSTANCE;
    }

    @Override
    public List<Resource> ruleBean2Resources(ThemisRuleBean themisRuleBean) throws IOException {
        List<Resource> resources = new ArrayList<>();
        List<ThemisFileBean> themisFileBeans = themisRuleBean.getThemisFileBeans();
        if (!CollectionUtils.isEmpty(themisFileBeans)) {
            for (ThemisFileBean themisFileBean: themisFileBeans) {
                if (themisFileBean.getType() == ThemisFileType.DRT) {
                    Collection<Object> collection = themisRuleBean.getCollectionMap().get(themisFileBean.getFileName());
                    themisFileBean.setContent(drt2drl(themisFileBean, collection));
                }
                resources.add(drl2Resource(themisFileBean));
            }
        }
        return resources;
    }

    public Resource drl2Resource(ThemisFileBean themisFileBean) {
        if (Strings.isNullOrEmpty(themisFileBean.getContent())) {
            throw new ThemisException("rule content is null");
        }
        return ResourceFactory.newByteArrayResource(themisFileBean.getContent().getBytes(StandardCharsets.UTF_8));
    }

    public String drt2drl(ThemisFileBean themisFileBean, Collection<Object> collection) throws IOException {
        if (Strings.isNullOrEmpty(themisFileBean.getContent())) {
            throw new ThemisException("template content is null");
        }
        if (CollectionUtils.isEmpty(collection)) {
            throw new ThemisException("collection is null");
        }
        Resource resource = ResourceFactory.newByteArrayResource(themisFileBean.getContent().getBytes(StandardCharsets.UTF_8));
        ObjectDataCompiler compiler = new ObjectDataCompiler();
        return compiler.compile(collection, resource.getInputStream());
    }
}
