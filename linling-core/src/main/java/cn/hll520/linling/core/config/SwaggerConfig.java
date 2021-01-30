package cn.hll520.linling.core.config;

import cn.hll520.linling.core.autovalue.AppInfoValue;
import cn.hll520.linling.core.autovalue.SwaggerInfoValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述： Swagger API 页面配置
 * <p>模式地址是   <b>/swagger-ui/index.html</b></p>
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-17-15:48
 * @since 2021-01-17-15:48
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final SwaggerInfoValue value;
    private final AppInfoValue appValue;

    /**
     * 构造函数自动注入
     *
     * @param value    {@link SwaggerInfoValue}
     * @param appValue {@link AppInfoValue}
     */
    public SwaggerConfig(SwaggerInfoValue value, AppInfoValue appValue) {
        this.value = value;
        this.appValue = appValue;
    }

    /**
     * 创建Rest APi 监听
     *
     * @return docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                .enable(value.isEnable())
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage(value.getBasePackagePath()))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Swagger 配置
     *
     * @return 配置
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称) 未配置就用应用名
                .title(value.getTitle() == null ? (appValue.getAppName() + " API文档") : value.getTitle())
                //文档描述  未配置就用应用描述
                .description(value.getDescription() == null ? appValue.getAppDescription() : value.getDescription())
                //服务条款URL 未配置就用应用首页
                .termsOfServiceUrl(value.getUrl() == null ? appValue.getAppHost() : value.getUrl())
                //版本号 未配置就用 应用版本
                .version(value.getVersion() == null ? appValue.getAppVersion() : value.getVersion())
                .build();
    }
}
