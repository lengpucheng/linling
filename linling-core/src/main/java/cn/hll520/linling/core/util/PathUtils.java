package cn.hll520.linling.core.util;

/**
 * 描述：服务地址字符串拼接
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-30-17:17
 * @since 2021-01-30-17:17
 */
public class PathUtils {
    /**
     * 拼接服务转发URL
     *
     * @param path 目标地址
     * @return forward:path
     */
    public static String forward(String path) {
        return "forward:" + path;
    }

    /**
     * 拼接服务重定向URL
     *
     * @param path 目标地址
     * @return redirect:path
     */
    public static String redirect(String path) {
        return "redirect:" + path;
    }
}
