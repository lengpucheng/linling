package cn.hll520.linling.core.exception;

/**
 * 描述：通用的 响应异常
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-29-20:12
 * @since 2020-12-29-20:12
 */
public class ServerException extends RuntimeException {
    /**
     * 错误代码 必须大于 1000
     */
    private int code;

    /**
     * 无参构造
     */
    public ServerException() {
        super();
    }

    /**
     * 带一个参数的构造
     *
     * @param message 错误信息
     */
    public ServerException(String message) {
        super(message);
        // 默认错误代码
        this.code = 1000;
    }

    /**
     * 两个参数的构造
     *
     * @param code    错误代码
     * @param message 错误信息
     */
    public ServerException(int code, String message) {
        this(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
