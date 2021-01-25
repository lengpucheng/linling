package cn.hll520.linling.core.object.safety;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： 用户身份认证 基类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-12:58
 * @since 2021-01-23-12:58
 */
@Data
public class UserBase implements Serializable {
    /**
     * 版本
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;

    public UserBase() {
    }

    public UserBase(String username) {
        this();
        this.username = username;
    }

    /**
     * 两个参数的构造
     *
     * @param username 用户名
     * @param password 密码
     */
    public UserBase(String username, String password) {
        this(username);
        this.password = password;
    }
}
