package cn.hll520.linling.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-20-20:37
 * @since 2021-01-20-20:37
 */
@SpringBootApplication(scanBasePackages = {"cn.hll520.linling"})
@ComponentScan({"cn.hll520.linling"})
@MapperScan({"cn.hll520.linling.*.mapper"})
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
