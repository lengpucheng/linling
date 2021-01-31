package cn.hll520.linling.core.config;

import cn.hll520.linling.core.autovalue.AutoSQLValue;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 描述：Mybatis Plus 动态SQL 自动填充配置处理器
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-31-22:01
 * @since 2021-01-31-22:01
 */
@Configuration
public class AutoSQLConfig implements MetaObjectHandler {

    private final AutoSQLValue autoSQLValue;

    public AutoSQLConfig(AutoSQLValue autoSQLValue) {
        this.autoSQLValue = autoSQLValue;
    }

    /**
     * 插入字段 时间 填充
     *
     * @param metaObject 源数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (autoSQLValue.isLocalDateTime()) {
            this.setFieldValByName(autoSQLValue.getCreateTime(), LocalDateTime.now(), metaObject);
        } else {
            this.setFieldValByName(autoSQLValue.getCreateTime(), new Date(), metaObject);
        }
    }

    /**
     * 更新字段 时间 填充
     *
     * @param metaObject 源数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (autoSQLValue.isLocalDateTime()) {
            this.setFieldValByName(autoSQLValue.getUpdateTime(), LocalDateTime.now(), metaObject);
        } else {
            this.setFieldValByName(autoSQLValue.getUpdateTime(), new Date(), metaObject);
        }
    }
}
