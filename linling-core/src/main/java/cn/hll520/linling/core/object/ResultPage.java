package cn.hll520.linling.core.object;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class ResultPage<T> {
    /**
     * 总数据量
     */
    @ApiModelProperty("总数据量")
    private long counts;
    /**
     * 当前页数据量
     */
    @ApiModelProperty("数据量")
    private long count;
    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private long pages;
    /**
     * 当前页数
     */
    @ApiModelProperty("页数")
    private long page;
    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", notes = "1000为不分页")
    private long pageSize;

    /**
     * 是否是首页
     */
    @ApiModelProperty("是否首页")
    private boolean isHead = true;

    /**
     * 是否是尾页
     */
    @ApiModelProperty("是否尾巴页")
    private boolean isEnd = false;


    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private List<T> data;

    public ResultPage() {

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
    public ResultPage(long count, long page, long pageSize, List<T> data) {
        this();
        this.count = count;
        this.page = page;
        this.pageSize = pageSize;
        this.data = data;
        this.pages = (count / pageSize) + (count % pageSize) == 0 ? 0 : 1;
        this.count = data == null ? 0 : data.size();
        this.isHead = page <= 0;
        this.isEnd = page >= this.pages;
    }

    /**
     * 根据分页结果生成标准分页结果集
     *
     * @param page ResultPage
     */
    public ResultPage(Page<T> page) {
        this(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }
}
