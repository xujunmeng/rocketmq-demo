package com.xujunmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author james
 * @date 2020/1/21
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class, ActiveMQAutoConfiguration.class})
@SpringBootApplication
public class RocketmqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqDemoApplication.class,args);
    }

}
