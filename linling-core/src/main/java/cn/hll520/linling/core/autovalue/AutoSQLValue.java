package cn.hll520.linling.core.autovalue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述： SQL 自动设置
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-31-21:56
 * @since 2021-01-31-21:56
 */
@ConfigurationProperties(prefix = "linling.core.auto-sql")
@Component
@Data
public class AutoSQLValue {
    /**
     * 是否是 localDateTime 否则为 Date
     */
    private boolean isLocalDateTime = true;
    /**
     * 插入时间字段 默认 createTime
     */
    private String createTime = "createTime";
    /**
     * 更新时间字段 默认 updateTime
     */
    private String updateTime = "updateTime";
    /**
     * 乐观锁字段  默认 versionLock
     */
    private String versionLock = "versionLock";
    /**
     * 逻辑删除字段 默认 logicDelete
     */
    private String logicDelete = "logicDelete";
}
