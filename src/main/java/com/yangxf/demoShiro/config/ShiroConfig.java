/**
 * FileName: ShiroConfig
 * Author:   linwd
 * Date:     2021/4/1 12:53
 * Description: Shiro使用的简单配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yangxf.demoShiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈Shiro使用的简单配置〉
 *
 * @author linwd
 * @create 2021/4/1
 * @since 1.0.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        TextConfigurationRealm textConfigurationRealm = new TextConfigurationRealm();
        /**
         * 简单范例，没有从数据库读取用户信息
         * 直接配置了两个用户，admin/123456和yangxf/123456
         * admin有读写权限，yangxf有读的权限
         */
        textConfigurationRealm.setUserDefinitions("admin=123456,admin\n yangxf=123456,user");
        textConfigurationRealm.setRoleDefinitions("admin=read,write\n yangxf=read");
        return textConfigurationRealm;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        /**
         * 配置基本过滤规则
         * /login以及/doLogin可以匿名访问
         * /logout是注销登录请求
         * /index需要认证后访问
         */
        defaultShiroFilterChainDefinition.addPathDefinition("/login", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/doLogin", "anon");
        defaultShiroFilterChainDefinition.addPathDefinition("/logout", "logout");
        defaultShiroFilterChainDefinition.addPathDefinition("/index", "authc");
        return defaultShiroFilterChainDefinition;

    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
