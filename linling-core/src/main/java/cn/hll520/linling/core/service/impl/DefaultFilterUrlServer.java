package cn.hll520.linling.core.service.impl;

import cn.hll520.linling.core.config.value.ShiroInfoValue;
import cn.hll520.linling.core.service.IFilterUrlServer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述： 默认是实现类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-26-19:05
 * @since 2021-01-26-19:05
 */
@Service
public class DefaultFilterUrlServer implements IFilterUrlServer {

    @Override
    public Map<String, String> filterUrl(Map<String, String> filter, ShiroInfoValue value) {
        List<ShiroInfoValue.FilterUrl> filterUrls = value.getFilter();
        // 添加配置中的
        if (filterUrls != null && filterUrls.size() > 0) {
            filterUrls.forEach(filterUrl -> {
                if (filterUrl.getUrl() != null)
                    filter.put(filterUrl.getUrl(), filterUrl.getRule());
            });
        }
        return filter;
    }
}
