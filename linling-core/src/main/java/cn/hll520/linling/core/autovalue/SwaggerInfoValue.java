package cn.hll520.linling.core.autovalue;

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
     * 设置Swagger基本扫描包 必须配置
     */
    private String basePackagePath = "cn.hll520.linling";
    /**
     * 当前API 版本
     */
    private String version = null;
    /**
     * Swagger 标题  不配置 首页 应用Name+API
     */
    private String title = null;
    /**
     * Swagger 页面的描述 不配置 首页 应用描述
     */
    private String description = null;
    /**
     * Swagger点击后跳转目录  不配置使用 应用首页
     */
    private String url = null;
}
