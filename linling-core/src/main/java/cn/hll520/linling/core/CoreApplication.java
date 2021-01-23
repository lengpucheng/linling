package cn.hll520.linling.core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述： 默认启动类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-19-21:29
 * @since 2021-01-19-21:29
 */
@SpringBootApplication(scanBasePackages = {"cn.hll520.linling"})
@ComponentScan({"cn.hll520.linling"})
//@MapperScan({"cn.hll520.linling"}) // 当实际使用 mybatis 时候 启用该注解
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
