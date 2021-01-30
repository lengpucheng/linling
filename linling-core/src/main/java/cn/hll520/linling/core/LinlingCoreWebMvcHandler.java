package cn.hll520.linling.core;

import cn.hll520.linling.core.autovalue.AppDefaultHostValue;
import cn.hll520.linling.core.autovalue.AppHostValue;
import cn.hll520.linling.core.util.PathUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 描述： 页面地址映射
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-30-17:00
 * @since 2021-01-30-17:00
 */
@Configuration
public class LinlingCoreWebMvcHandler implements WebMvcConfigurer {
    private final AppHostValue value;

    public LinlingCoreWebMvcHandler(AppHostValue value) {
        this.value = value;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        checkAdd(registry, value.getAppInfoHost(), AppDefaultHostValue.appInfoHost, true);
        checkAdd(registry, value.getAppDefaultLoginHost(), AppDefaultHostValue.appLoginHost, true);
        checkAdd(registry, value.getAppCoreManageHost(), AppDefaultHostValue.appCoreManageHost, true);
        checkAdd(registry, value.getAppApiHost(), AppDefaultHostValue.appApiHost, false);
        checkAdd(registry, value.getAppSQLManageHost(), AppDefaultHostValue.appSQLManageHost, false);
    }

    /**
     * 判断是否默认并添加
     *
     * @param registry    reg
     * @param path        自定义url
     * @param isForward   是否forward
     * @param defaultPath 默认url
     */
    private void checkAdd(ViewControllerRegistry registry, String path, String defaultPath, boolean isForward) {
        if (registry == null || path == null || defaultPath == null) {
            return;
        }
        if (!defaultPath.equals(path)) {
            if (isForward) {
                registry.addViewController(path).setViewName(PathUtils.forward(defaultPath));
            } else {
                registry.addViewController(path).setViewName(PathUtils.redirect(defaultPath));
            }
        }
    }
}
