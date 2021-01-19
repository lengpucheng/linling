package cn.hll520.linling.core.object;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述：标准分页响应
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-19-22:13
 * @since 2021-01-19-22:13
 */
@ApiModel("标准分页响应结果")
@Data
public class ResultList<T> {
    /**
     * 总数据量
     */
    @ApiModelProperty("总数据量")
    private int counts;
    /**
     * 当前页数据量
     */
    @ApiModelProperty("数据量")
    private int count;
    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private int pages;
    /**
     * 当前页数
     */
    @ApiModelProperty("页数")
    private int page;
    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", notes = "1000为不分页")
    private int pageSize;
    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private List<T> data;

    public ResultList() {

    }

    /**
     * 使用四个参数构造
     * <p><b>将自动计算页数和数据量</b>
     *
     * @param count    总数量
     * @param page     当前页
     * @param pageSize 总页数
     * @param data     数据
     */
    public ResultList(int count, int page, int pageSize, List<T> data) {
        this();
        this.count = count;
        this.page = page;
        this.pageSize = pageSize;
        this.data = data;
        this.pages = (count / pageSize) + (count % pageSize) == 0 ? 0 : 1;
        this.count = data == null ? 0 : data.size();
    }
}
