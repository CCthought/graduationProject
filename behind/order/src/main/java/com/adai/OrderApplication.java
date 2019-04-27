package com.adai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * author Adai
 * create 2019-03-01  13:52
 *  dashboard访问路口
 *  http://localhost:8084/hystrix
 * Hystrix Dashboard输入： http://localhost:8084/actuator/hystrix.stream
 *
 */
@SpringBootApplication
@MapperScan({"com.adai.dao"})
@EnableTransactionManagement
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
