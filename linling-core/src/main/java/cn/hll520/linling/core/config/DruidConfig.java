package cn.hll520.linling.core.config;

import cn.hll520.linling.core.autovalue.DruidInfoValue;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：Druid SQL 监控中心配置
 * <p>默认地址是 <b>/druid/login.html</b></p>
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-17-17:24
 * @since 2021-01-17-17:24
 */
@Configuration
public class DruidConfig {
    private final DruidInfoValue value;

    /**
     * 自动注入的配置构造
     *
     * @param value {@link DruidInfoValue}
     */
    public DruidConfig(DruidInfoValue value) {
        this.value = value;
    }

    /**
     * druidServlet 监管中心配置
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*"
        );
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", value.getUsername());
        servletRegistrationBean.addInitParameter("loginPassword", value.getPassword());
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", String.valueOf(value.isRestEnable()));
        return servletRegistrationBean;
    }

    /**
     * druid 过滤
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new WebStatFilter());
        // 管理的路径
        filterRegistrationBean.addUrlPatterns(value.getUrlPatterns());
        // 管理的格式
        filterRegistrationBean.addInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
