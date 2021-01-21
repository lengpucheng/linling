package cn.hll520.linling.core.object;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 描述： 菜单
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-21-15:24
 * @since 2021-01-21-15:24
 */
@Component
@Data
public class Menu {

    /**
     * id 唯一标识
     */
    private long id = System.currentTimeMillis();
    /**
     * 名称
     */
    private String name;
    /**
     * 跳转地址  默认不跳转
     */
    private String url = "#";
    /**
     * 连接打开窗口 默认null=新窗口="_blank"
     */
    private String target = null;
    /**
     * 父级菜单id  0表示没有父级菜单
     */
    private long superMenuId = 0;

    public Menu() {
    }

    public Menu(String name, String url) {
        this();
        this.name = name;
        this.url = url;
    }

    public Menu(String name, String url, String target) {
        this(name, url);
        this.target = target;
    }
}
