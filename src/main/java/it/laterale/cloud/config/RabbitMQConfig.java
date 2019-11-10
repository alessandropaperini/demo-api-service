package it.laterale.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * The type Rabbit mq config.
 */
@Configuration
@Slf4j
public class RabbitMQConfig {

    @Autowired
    private Environment env;

    /**
     * Ball queue queue.
     *
     * @return the queue
     */
    @Bean
    public Queue ballQueue() {
        return createQueue(this.env.getProperty("spring.rabbitmq.queue.ball.name"));
    }

    private Queue createQueue(String name) {
        log.debug("Creating queue {}", name);
        return new Queue(name);
    }

}