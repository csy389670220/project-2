package com.example.ips;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.example.ips.mapper")
public class IpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpsApplication.class, args);
    }

}
