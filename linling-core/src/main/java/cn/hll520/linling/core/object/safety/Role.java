package cn.hll520.linling.core.object.safety;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：身份
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-13:03
 * @since 2021-01-23-13:03
 */
@Builder
@Data
public class Role implements Serializable {
    /**
     * 版本
     */
    private static final long serialVersionUID = 1L;

    /**
     * 身份id
     */
    private int rid;
    /**
     * 身份名称
     */
    private String roleName;
    /**
     * 说明
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 身份ID代码
     */
    private String roleCode;

    @Tolerate
    public Role() {
    }

}
