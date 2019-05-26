package com.yahpoo.oneboot.config.mybaitsplus;


import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * TODO mybatis-plus配置
 */

@Configuration
@MapperScan
public class MybaitsPlusConfig {




    /**
     * TODO 拦截性能分析器
     * @return
     */
    @Bean
    @Profile({"local", "dev", "uat"})
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }
}
