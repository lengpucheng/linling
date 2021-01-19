package cn.hll520.linling.core.config.value;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述： API 接口 监管 配置
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-17-16:59
 * @since 2021-01-17-16:59
 */
@ConfigurationProperties(prefix = "linling.core.swagger")
@Component
@Data
public class SwaggerInfoValue {

    /**
     * 是否启用swagger
     */
    private boolean enable = false;
    /**
     * 设置Swagger基本扫描包
     */
    private String basePackagePath = "cn.hll520.linling";
    /**
     * 当前API 版本
     */
    private String version = "v1.0";
    /**
     * Swagger 标题
     */
    private String title = "LinlingCore API";
    /**
     * Swagger 页面的描述
     */
    private String description = "LinlingCore API 文档描述 \n Power by LPC";
    /**
     * Swagger点击后跳转目录
     */
    private String url = "/";
}
