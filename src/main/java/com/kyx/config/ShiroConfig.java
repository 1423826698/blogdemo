package com.kyx.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.kyx.shiro.AuthRealm;
import com.kyx.shiro.CustomSessionIdGenerator;
import com.kyx.shiro.CustomSessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Value("127.0.0.1")
    private String host;
    @Value("6379")
    private Integer port;
    /**
     *  配置shiro和spring关联
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager){
        AuthorizationAttributeSourceAdvisor advisor =new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    @Bean("hashedCredentialsMarcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
            HashedCredentialsMatcher hashedCredentialsMatcher =new HashedCredentialsMatcher();
            //加密方式
            hashedCredentialsMatcher.setHashAlgorithmName("MD5");
            //加密次数
            hashedCredentialsMatcher.setHashIterations(1024);
            hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
            return hashedCredentialsMatcher;
    }
    /**
     *  自定义realm
     * @return
     */
    @Bean("authRealm")
    public AuthRealm authRealm(){
        AuthRealm authRealm =new AuthRealm();
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return authRealm;
    }

    /**
     *  自定义sessionManager
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager(){
        CustomSessionManager  customSessionManager=new CustomSessionManager();
        // 超时时间 默认30分钟，会话超时，方法里面的是单位是毫秒
        customSessionManager.setGlobalSessionTimeout(60*1000*60*24);
        //配置session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }
    @Bean("securityManager")
    public SessionsSecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager =new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(sessionManager());
        //设置缓存
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        defaultWebSecurityManager.setRealm(authRealm());
        return defaultWebSecurityManager;
    }
    /**
     *设置redisManager
     * @return
     */
    public RedisManager getRedisManager(){
        RedisManager redisManager =new RedisManager();
        redisManager.setPort(port);
        redisManager.setHost(host);
        return  redisManager;
    }

    /**
     * 配置具体cache实现类
     * @return
     */
    @Bean("redisCacheManager")
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        // 设置过期时间，单位是秒
        redisCacheManager.setExpire(60 * 60 * 24);
        return redisCacheManager;
    }

    /**
     * 自定义session持久化
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        // 设置sessionId生成器
        redisSessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());
        return redisSessionDAO;
    }
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean =new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setUnauthorizedUrl("/");
        bean.setLoginUrl("login");
        Map<String,String> map =new LinkedHashMap<>();
        map.put("/", "anon");    // authc --    认证(登录)才能使用
        map.put("/user", "authc");
        map.put("/editor", "roles[admin]");
        map.put("/admin", "roles[admin]");
        map.put("/druid/**", "anon");  // anon -- 匿名访问
        map.put("/**", "anon");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     *Shiro控制Thymeleaf界面控制限权控制
     * @return
     */
    @Bean("shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


    /**
     * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理 .
     *
     * @return
     */
}
