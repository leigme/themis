package me.leig.project.themis.configuration;

import me.leig.project.themis.context.ThemisContext;
import me.leig.project.themis.context.impl.ThemisContextImpl;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
@Configuration
public class ThemisConfiguration {

    @Bean
    public KnowledgeBuilder knowledgeBuilder() {
        return KnowledgeBuilderFactory.newKnowledgeBuilder();
    }

    @Bean
    public InternalKnowledgeBase internalKnowledgeBase() {
        return KnowledgeBaseFactory.newKnowledgeBase();
    }

    @Bean
    public ThemisContext themisContext() {
        return new ThemisContextImpl();
    }
}
