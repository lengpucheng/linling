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
     * 页数
     */
    private Integer page = 0;
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
     * @param page 页数 从0开始
     */
    public QueryParams(Integer page) {
        this();
        this.page = page == null ? 0 : page;
        this.offset = this.page * this.limit;
    }

    /**
     * 两个参数
     *
     * @param limit 每页大小
     * @param page  页数
     */
    public QueryParams(Integer limit, Integer page) {
        this(page);
        this.limit = limit == null ? 20 : limit;
    }

    /**
     * 全量构造
     *
     * @param limit   每页大小
     * @param page    页数
     * @param orderBy 排序字段
     * @param order   排序方式
     */
    public QueryParams(Integer limit, Integer page, String orderBy, Order order) {
        this(limit, page);
        this.orderBy = orderBy;
        this.order = order == null ? Order.DESC : order;
    }

    /**
     * 偏移量构造
     *
     * @param offset 偏移量
     */
    public static QueryParams build(Integer offset) {
        return build(20, offset);
    }

    /**
     * 偏移量构造
     *
     * @param limit  每页大小
     * @param offset 偏移量
     */
    public static QueryParams build(Integer limit, Integer offset) {
        return QueryParams.build(limit, offset, null, null);
    }

    /**
     * 全量偏移量构造
     *
     * @param limit   每页大小
     * @param offset  偏移量
     * @param orderBy 排序字段
     * @param order   排序方式
     */
    public static QueryParams build(Integer limit, Integer offset, String orderBy, Order order) {
        QueryParams queryParams = new QueryParams(limit,
                limit == null ? offset / 20 : (limit == 0 ? 0 : offset / limit),
                orderBy, order);
        queryParams.setOffset(offset);
        return queryParams;
    }
}
