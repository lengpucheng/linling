package cn.hll520.linling.core.object;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述： 菜单组 一组菜单
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-21-15:56
 * @since 2021-01-21-15:56
 */
@Component
@Data
public class MenuGroup {
    /**
     * 组名称
     */
    private String name;
    /**
     * 组内的 菜单
     */
    private List<Menu> menus;
    /**
     * 子组
     */
    private List<MenuGroup> menuGroups;
}
