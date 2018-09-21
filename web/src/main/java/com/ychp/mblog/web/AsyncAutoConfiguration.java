package com.ychp.mblog.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author yingchengpeng
 * @date 2018-08-09
 */
@Configuration
@EnableAsync
public class AsyncAutoConfiguration {

    /**
     * 自定义异步线程池
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor");
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(5);

        // 设置拒绝策略
        executor.setRejectedExecutionHandler((r, executor1) -> {
            // .....
        });
        // 使用预定义的异常处理类 todo
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }
}
