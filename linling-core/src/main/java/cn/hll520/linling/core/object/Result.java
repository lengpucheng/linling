package cn.hll520.linling.core.object;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述： 请求的标准结果
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-28-21:01
 * @since 2020-12-28-21:01
 */
@ApiModel("标准响应结果")
@Data
public class Result<T> {
    /**
     * 请求是否成功
     * <p><b>这里指的是请求是否通常 不代表服务成功</b>
     */
    @ApiModelProperty(value = "服务状态", notes = "这里指的是是否达到预定的请求")
    private boolean success;

    /**
     * 服务的返回代码
     * <p><b>这里这是表明在请求成功后服务是否遇到问题或状态</b></p>
     *
     * <p>-200 表示成功</p>
     * <p>-110表示需要登录</p>
     * <p>-119表示权限不足</p>
     * <p>-1000以上是失败后的错误代码</p>
     */
    @ApiModelProperty(value = "回执代码",
            notes = "服务的返回代码 200 表示成功 110表示需要登录 119表示权限不足 1000以上是失败后的错误代码")
    private int code;

    /**
     * 服务返回的信息
     */
    @ApiModelProperty(value = "回执消息")
    private String message;

    /**
     * 实际返回的数据
     */
    @ApiModelProperty("响应数据")
    private T data;

    /**
     * 无参数构造
     */
    public Result() {
    }

    /**
     * 不带响应数据的构造
     * <p><b>这里这是表明请求是否遇到问题与服务无关</b></p>
     *
     * <p>-200 表示成功</p>
     * <p>-500表示需要登录</p>
     * <p>-400表示权限不足</p>
     * <p>-1000以上是失败后的错误代码</p>
     *
     * @param success 是否成功
     * @param code    代码
     * @param message 消息
     */
    public Result(boolean success, int code, String message) {
        this();
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 不带响应数据的构造
     * <p><b>这里这是表明请求是否遇到问题与服务无关</b></p>
     *
     * <p>-200 表示成功</p>
     * <p>-500表示需要登录</p>
     * <p>-400表示权限不足</p>
     * <p>-1000以上是失败后的错误代码</p>
     *
     * @param success 是否成功
     * @param code    代码
     * @param message 消息
     * @param data    响应的数据
     */
    public Result(boolean success, int code, String message, T data) {
        this(success, code, message);
        this.data = data;
    }
}
