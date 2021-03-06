package cn.hll520.linling.core.autovalue;

import cn.hll520.linling.core.object.Menu;
import cn.hll520.linling.core.object.MenuGroup;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述： App 后台配置
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-21-15:46
 * @since 2021-01-21-15:46
 */
@ConfigurationProperties(prefix = "linling.core.manage")
@Component()
@Data
public class AppManageValue {
    /**
     * 页面标签 默认为 APP-Name+控制台
     */
    private String title = null;

    /**
     * 页面大标题 默认为APP-Name
     */
    @NestedConfigurationProperty
    private Menu top = null;

    /**
     * 头部菜单</p>
     * <p> - name: menu1</p>
     * <p>   url: url</p>
     * <p>   target: target 默认为 _view</p>
     * <p> - name: menu2....</p>
     * <p>若为null 默认为 API接口、SQL监控和关于应用</p>
     */
    private List<Menu> navbar = null;

    /**
     * 左侧分组菜单 {@code List<menu> 是菜单集} 外部为分组
     * <p> - name: group1</p>
     * <p>   menu:</p>
     * <p>    - name: menu1</p>
     * <p>      url: url</p>
     * <p>      target: target 默认为_view</p>
     * <p>    - name: menu2 ....</p>
     * <p> - name: group2 ....</p>
     * <p><b>暂时不支持子组</b></p>
     */
    private List<MenuGroup> menu = null;

    /**
     * 默认打开页面
     * <p>为空自动使用 MenuGroup 的第一个</p>
     * <b>若menuGroup为null 默认为AppHost</b>
     */
    private String welcome = null;


}
