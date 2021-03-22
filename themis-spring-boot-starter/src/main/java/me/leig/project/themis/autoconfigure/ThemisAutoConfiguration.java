package me.leig.project.themis.autoconfigure;

import me.leig.project.themis.configuration.ThemisConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
@Configuration
@Import(ThemisConfiguration.class)
public class ThemisAutoConfiguration {
}
