package com.ychp.mblog.web;

import com.ychp.async.publisher.AsyncPublisher;
import com.ychp.async.subscriber.Dispatcher;
import com.ychp.async.subscriber.SubscriberRegistry;
import com.ychp.blog.impl.BlogAutoConfiguration;
import com.ychp.common.captcha.CaptchaGenerator;
import com.ychp.file.cos.CosAutoConfiguration;
import com.ychp.ip.IPServiceAutoConfiguration;
import com.ychp.mblog.web.interceptors.SessionInterceptor;
import com.ychp.mblog.web.session.SessionManager;
import com.ychp.user.UserApiAutoConfig;
import com.ychp.user.impl.UserAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yingchengpeng
 * @date 2018-08-09
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.ychp.mblog.web", "com.ychp.redis"})
@Import({IPServiceAutoConfiguration.class,
        CosAutoConfiguration.class,
        UserApiAutoConfig.class,
        UserAutoConfiguration.class,
        BlogAutoConfiguration.class})
public class WebAutoConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public SessionManager sessionManager() {
        return new SessionManager();
    }

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(sessionInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public CaptchaGenerator captchaGenerator() {
        return new CaptchaGenerator();
    }

    @Bean
    public SubscriberRegistry subscriberRegistry() {
        return new SubscriberRegistry();
    }

    @Bean
    public Dispatcher dispatcher(SubscriberRegistry subscriberRegistry) {
        return new Dispatcher(subscriberRegistry);
    }

    @Bean
    public AsyncPublisher asyncPublisher() {
        return new AsyncPublisher();
    }
}
