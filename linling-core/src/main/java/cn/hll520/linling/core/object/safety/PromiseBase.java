package cn.hll520.linling.core.object.safety;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： 权限基类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-13:06
 * @since 2021-01-23-13:06
 */
@Builder
@Data
public class PromiseBase implements Serializable {
    /**
     * 版本
     */
    private static final long serialVersionUID = 1L;
    /**
     * 权限id
     */
    private Integer pid;
    /**
     * 权限名称
     */
    private String promiseName;
    /**
     * 说明
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 权限编码
     */
    private String promiseCode;

    @Tolerate
    public PromiseBase() {
    }
}
