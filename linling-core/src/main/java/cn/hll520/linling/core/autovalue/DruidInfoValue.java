package cn.hll520.linling.core.autovalue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 描述： SQL 监管中心
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-19-21:44
 * @since 2021-01-19-21:44
 */

@ConfigurationProperties(prefix = "linling.core.sql")
@Component
@Data
public class DruidInfoValue {
    /**
     * 设置监控中心的用户名
     */
    private String username = "admin";
    /**
     * 设置监控中心的密码
     */
    private String password = "admin";
    /**
     * 启用重制按钮
     */
    private boolean restEnable = false;
    /**
     * 管理的路径
     */
    private String urlPatterns = "/*";
}
