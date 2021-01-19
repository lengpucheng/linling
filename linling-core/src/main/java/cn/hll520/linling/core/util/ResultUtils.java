package cn.hll520.linling.core.util;

import cn.hll520.linling.core.exception.ServerException;
import cn.hll520.linling.core.object.Result;

/**
 * 描述：用于生成一些常用的结果
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-20:36
 * @since 2020-12-15-20:36
 */
@SuppressWarnings("all")
public class ResultUtils {
    /*
     * ————————————————————————
     *   异常和错误结果
     * ————————————————————————
     * */

    /**
     * 根据 异常 来 返回 错误监管
     *
     * @param exception 异常
     * @return 失败的结果
     */
    public static Result error(Exception exception) {
        if (exception instanceof ServerException) {
            ServerException e = (ServerException) exception;
            return fail(e.getMessage(), e.getCode());
        }
        return fail(exception.getMessage());
    }

    /*
     * ————————————————————————
     *   身份和权限认证结果
     * ————————————————————————
     * */

    /**
     * 返回带未登录标识的失败结果
     *
     * @param msg 错误信息
     * @return 带为登录的结果
     */
    public static Result noLogin(String msg) {
        return new Result(false, 500, "请先登录！" + (msg == null ? "" : msg), null);
    }

    /**
     * 权限不足
     *
     * @param msg 信息
     * @return 带没有权限的结果
     */
    public static Result noPermit(String msg) {
        return new Result(false, 400, "没有权限！" + (msg == null ? "" : msg), null);
    }


    /*
     * ————————————————————————
     *   boolean 结果
     * ————————————————————————
     * */

    /**
     * 根据Boolean 返回一个带消息Boolean 类型的结果
     *
     * @param flag boolean
     * @param msg  消息
     * @return 带消息的是否结果
     */
    public static Result<Boolean> bool(boolean flag, String msg) {
        return build(flag, flag, msg);
    }

    /**
     * 根据Boolean 返回一个Boolean 类型的结果
     *
     * @param flag boolean
     * @return 带消息的是否结果
     */
    public static Result<Boolean> bool(boolean flag) {
        return build(flag, flag);
    }


    /*
     * ————————————————————————
     *   成功的结果
     * ————————————————————————
     * */

    /**
     * 返回一个带数据的成功结果
     *
     * @param data 数据
     * @param <T>  和输入类容一样的结果
     * @return 标准结果
     */
    public static <T> Result<T> success(T data) {
        return success(data, null);
    }

    /**
     * 返回只有带消息的成功结果
     *
     * @param msg 消息
     * @return 成功
     */
    public static Result success(String msg) {
        return success(null, msg);
    }

    /**
     * 返回带数据和消息的成功结果
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  结果类型
     * @return 结果
     */
    public static <T> Result<T> success(T data, String msg) {
        return build(true, data, msg);
    }


    /*
     * ————————————————————————
     *   失败的结果
     * ————————————————————————
     * */

    /**
     * 带消息的失败结果
     *
     * @param msg 消息
     * @return 带消息失败结果
     */
    public static Result fail(String msg) {
        return fail(null, msg);
    }

    /**
     * 带消息和指定code的失败结果
     *
     * @param msg 消息
     * @return 带消息失败结果
     */
    public static Result fail(String msg, int code) {
        return fail(null, msg, code);
    }

    /**
     * 返回带数据和消息的失败结果
     * <b>code默认1000</b>
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  结果类型
     * @return 失败的结果
     */
    public static <T> Result<T> fail(T data, String msg) {
        return fail(data, msg, 1000);
    }

    /**
     * 返回带数据、消息和自定义错误代码的失败结果
     *
     * @param data 数据
     * @param msg  消息
     * @param code 错误代码
     * @param <T>  结果类型
     * @return 失败的结果
     */
    public static <T> Result<T> fail(T data, String msg, int code) {
        return new Result<>(false, code, msg, data);
    }


    /*
     * ————————————————————————
     *   通用的结果
     * ————————————————————————
     * */

    /**
     * 构造通用的方法
     *
     * @param flag 是否成功
     * @param data 数据
     * @param <T>  数据类型
     * @return 自动判断code的结果集
     */
    public static <T> Result<T> build(boolean flag, T data) {
        return build(flag, data, null);
    }

    /**
     * 构造通用的方法
     *
     * @param flag 是否成功
     * @param data 数据
     * @param msg  消息
     * @param <T>  数据类型
     * @return 自动判断code的结果集
     */
    public static <T> Result<T> build(boolean flag, T data, String msg) {
        return new Result<>(flag, flag ? 200 : 1000, msg, data);
    }
}
