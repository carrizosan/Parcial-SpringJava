package utn.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
//EJERCICIO 3
public class AsyncConfiguration {

    Integer CORE_POOL_SIZE = 100;
    Integer MAX_POOL_SIZE = 100;
    Integer QUEUE_CAPACITY = 500;

    @Bean("threadPoolTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.initialize();
        return executor;
    }
}
