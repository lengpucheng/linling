package cn.hll520.linling.core.object.safety;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * 描述：身份基类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-13:03
 * @since 2021-01-23-13:03
 */
@Builder
@Data
public class RoleBase implements Serializable {
    /**
     * 版本
     */
    private static final long serialVersionUID = 1L;
    /**
     * 身份rid
     */
    private Integer rid;
    /**
     * 身份名称
     */
    private String roleName;
    /**
     * 身份ID代码
     */
    private String roleCode;

    @Tolerate
    public RoleBase() {
    }

}
