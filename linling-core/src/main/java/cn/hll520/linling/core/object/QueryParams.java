package cn.hll520.linling.core.object;

import lombok.Data;

/**
 * 描述：查询参数
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-27-21:43
 * @since 2021-01-27-21:43
 */
@Data

public class QueryParams {
    /**
     * 每页大小 默认20
     */
    private Integer limit = 20;
    /**
     * 偏移量 默认0
     */
    private Integer offset = 0;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * 排序方式
     */
    private Order order = Order.DESC;

    /**
     * 无参构造
     */
    public QueryParams() {
    }

    /**
     * 默认构造
     *
     * @param offset 偏移量
     */
    public QueryParams(Integer offset) {
        this();
        this.offset = offset;
    }

    /**
     * 两个参数
     *
     * @param limit  每页大小
     * @param offset 偏移量
     */
    public QueryParams(Integer limit, Integer offset) {
        this(limit);
        this.offset = offset;
    }

    /**
     * 全量构造
     *
     * @param limit   每页大小
     * @param offset  偏移量
     * @param orderBy 排序字段
     * @param order   排序方式
     */
    public QueryParams(Integer limit, Integer offset, String orderBy, Order order) {
        this(limit, offset);
        this.orderBy = orderBy;
        this.order = order;
    }

}
