/**
 * 描述： linling core 封装方法 必须引入 jquery
 * <p>这里是封装的 一些全局方法
 * <p>-常用方法(登录/出、获取信息、时间、缓存读写)
 * <p>-HTTP请求(参数、JSON参数、带登录验证、带权限验证)
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-16-20:31
 * @since 2020-12-16-20:31
 */
/**
 * 缓存名称
 * @type {string}
 */
let caChe_Name = "linling_core_";
/**
 * App info 请求
 * @type {string}
 */
let CACHE_APP_INFO = "appInfo_cache";
/**
 * 被拦截的URL 临时缓存
 * @type {string}
 */
let TEMPORARY_BAN_URL = "temporary_BAN_URL";

/**
 * 获取登录信息
 * @type {string}
 */
let HOST_IDENTITY = "/linling/identity";
/**
 * 登录请求地址
 * @type {string}
 */
let HOST_LOGIN = "/linling/identity/login";
/**
 * 登出
 * @type {string}
 */
let HOST_LOGOUT = "/linling/identity/logout";

/**
 * 标准的返回对象
 * @param success 请求是否成功
 * @param code 服务响应码 200成功  1000+ 失败  500 未登录 400 权限不足
 * @param message 请求附带消息 多为错误消息
 * @param data 请求实际数据
 * @constructor result
 */
function Result(success, code, message, data) {
    this.success = success;
    this.code = code;
    this.message = message;
    this.data = data;
}

/**
 * 写数据到本地缓存
 * @param key key
 * @param value 数据
 */
function saveLocal(key, value) {
    let obj = null;
    if (value !== null && value !== undefined) {
        obj = JSON.stringify(value);
    }
    localStorage.setItem(caChe_Name + key, obj);
}

/**
 * 从本地缓存读取数据
 * @param key key
 * @returns {any} 数据对象
 */
function getLocal(key) {
    let value = localStorage.getItem(caChe_Name + key);
    if (value === null || value === undefined || value === "")
        return null;
    return JSON.parse(value);
}

/**
 * 获取 AppInfo 信息
 * appHost  --app首页
 * appCoreManageHost  --app后台页
 * appLoginHost  --app登录页
 * appSQLManage  --appSQL监控页
 * appApiHost --appAPI信息页
 * appInfoHost  --appInfo页
 * appName  --app名称
 * appDescription  --app描述
 * appVersion  --app版本
 * appAuthor  --app作者信息
 * - name  --姓名
 * - email  --邮件
 * - githubUrl  --github
 * - webName  --web名称
 * - webSite  --webUrl
 * @returns {*} appInfo
 */
function getAppInfo() {
    let appInfo = getLocal(CACHE_APP_INFO);
    // 若未获取就获取
    if (appInfo === null || appInfo === undefined || appInfo.appName === null || appInfo.appName === undefined) {
        // 请求获取 并保存信息
        httpGo("/linling/core/appInfo", {}, function (data) {
            if (data.success || data.code === 200) {
                saveLocal(CACHE_APP_INFO, data.data);
            }
        });
    }
    // 若不存在则设置默认地址
    if (appInfo.hosts.appLoginHost === null || appInfo.hosts.appLoginHost === undefined) {
        appInfo.hosts.appLoginHost = "/linling/login.html";
    }
    if (appInfo.infos.appHost === null || appInfo.infos.appHost === undefined) {
        appInfo.infos.appHost = "/";
    }
    return appInfo;
}

/**
 * 1、有参数-转换为 YYYY-MM-DD
 * 2、无参数-获取当前日期  YYYY-MM-DD
 * @param date 日期 为空则 为当前
 * @returns {*}  YYYY-MM-DD
 */
function dateToString(date) {
    if (date === undefined || date === null) {
        date = new Date();
    }
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;
    return year + "-" + month + "-" + day;
}

/**
 * 1、有参数-转换为 YYYY-MM-DD HH MM SS
 * 2、无参数-获取当前时间  YYYY-MM-DD HH MM SS
 * @param date 日期 为空则 为当前
 * @returns {*}  YYYY-MM-DD HH MM SS
 */
function dateAndTimeToString(date) {
    if (date === undefined || date === null) {
        date = new Date();
    }
    let hours = date.getHours();
    let mins = date.getMinutes();
    let secs = date.getUTCSeconds();
    if (hours < 10) hours = "0" + hours;
    if (mins < 10) mins = "0" + mins;
    if (secs < 10) secs = "0" + secs;
    return dateToString(date) + " " + hours + ":" + mins + ":" + secs;
}

/**
 *在一个弹窗中打卡页面
 * @param url 转向网页的地址;
 * @param name 网页名称，可为空;
 * @param iWidth 弹出窗口的宽度;
 * @param iHeight 弹出窗口的高度;
 */
function openWindow(url, name, iWidth, iHeight) {
    //window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
    let iTop = (window.screen.height - 30 - iHeight) / 2; //获得窗口的垂直位置;
    let iLeft = (window.screen.width - 10 - iWidth) / 2; //获得窗口的水平位置;
    if (name === null || name === undefined)
        name = "窗口";
    if (iHeight === null || iHeight === undefined)
        iHeight = window.screen.height / 2;
    if (iWidth === null || iWidth === undefined)
        iWidth = window.screen.height / 2;
    window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

/*
*————————————————————————
*  以下为 封装的 身份认证
*————————————————————————
* */
/**
 * 判断是否登录(获取用户信息)
 * @param success 是的回调 默认是存储 user
 * @param fail 否的回调    默认跳转到登录页面
 */
function isLogin(success, fail) {
    let user = getLocal("user");
    // user 不存在 认为是未登录
    if (user === null || user === undefined) {
        alert("请先登录！\n");
        // 保存当前被拦截的页面url
        saveLocal(TEMPORARY_BAN_URL, window.location.href);
        if (fail !== null && fail !== undefined) {
            fail(new Result(false, 110, "未登录", null));
        } else {
            window.location.href = getAppInfo().hosts.appLoginHost;
        }
        return;
    }
    // 构造回调
    let call = function (result) {
        // 未登录
        if (!result.success) {
            alert("请先登录！\n" + result.message);
            // 保存当前被拦截的页面url
            saveLocal(TEMPORARY_BAN_URL, window.location.href);
            if (fail !== null && fail !== undefined) {
                fail(result);
            } else {
                window.location.href = getAppInfo().hosts.appLoginHost;
            }
            return;
        }
        // 已登录 写入/更新 用户信息
        saveLocal("user", result.data);
        if (success !== null && success !== undefined)
            success(result);
    }
    // 请求
    httpGoLogin(HOST_IDENTITY, {}, call, "GET");
}

/**
 * 退出登录
 */
function doLogout() {
    httpGoLogin(HOST_LOGOUT, {}, function (result) {
        if (!result.success) {
            alert("退出登录失败！" + result.msg);
            return;
        }
        saveLocal("user", null);
        window.location.href = getAppInfo().hosts.appLoginHost;
    });
}


/**
 * 登录方法  若之前有被拦截的页面 登录成功后会额外打卡一个新窗口为该页面
 * @param username 用户名
 * @param password 密码
 * @param remember 是否记住
 * @param successHost 登录后跳转页面  所设置 success 本项将不会起作用
 * @param success 成功回调
 */
function doLogin(username, password, remember, successHost, success) {
    if (username === null || username === undefined || password === null || password === undefined) {
        alert("用户名或密码不能为空");
        return;
    }
    // 如果没有给回调函数 用默认
    if (success === null || success === undefined) {
        success = function (result) {
            if (!result.success) {
                alert("登录失败！" + result.message);
                return;
            }
            // 写入缓存
            saveLocal("user", result.data);
            let banHost = getLocal(TEMPORARY_BAN_URL);
            // 如果被拦截不为null 就在一个新的窗口打开
            if (banHost !== null && banHost !== undefined) {
                saveLocal(TEMPORARY_BAN_URL, null);
                window.open(banHost, "_blank");
            }
            // 当前页面跳转到设置页面
            window.location.href = successHost === null || successHost === undefined ? "/" : successHost;
        }
    }
    httpGo(HOST_LOGIN, {username: username, password: password, remember: remember}, success, "POST");

}


/*
*————————————————————————
*  以下为 封装的 Http 请求
*————————————————————————
* */

/**
 *  带登录和权限验证的 HttpJson 请求
 * @param url 地址，可以是相对地址
 * @param data 数据，形式为 map 键值对{a:"",b:""};  -- 不可空，若空必须为{}
 * @param success 请求成功后的回调函数，会判断是否登录，否则会跳转登录页面
 * @param method  请求方式，默认GET，形式为”POST/GET/“  --可空
 * @param noPromiseMsg 权限不足的弹窗消息 -- 可空
 * @param error   错误的回调，默认会提升网络错误 --可空
 */
function httpGoJsonPromise(url, data, success, method, noPromiseMsg, error) {
    let successPromise = function (result) {
        if (result.code == 110 || result.code == 120) {
            noPromiseMsg = noPromiseMsg === undefined || noPromiseMsg === null ? "权限不足" : noPromiseMsg;
            alert(noPromiseMsg + "\n" + result.message);
            return;
        }
        if (success !== null && success !== undefined) {
            success(result);
        }
    }
    httpJsonGo(url, data, successPromise, method, error);
}

/**
 * 带登录验证的 Http 请求
 * @param url 地址，可以是相对地址
 * @param data 数据，形式为 map 键值对{a:"",b:""};
 * @param success 请求成功后的回调函数，会判断是否登录，否则会跳转登录页面
 * @param method  请求方式，默认GET，形式为”POST/GET/“  --可空
 * @param error   错误的回调，默认会提升网络错误  --可空
 */
function httpGoLogin(url, data, success, method, error) {
    // 添加未登录判断
    let successLogin = function (result) {
        if (result.code == 110) {
            // 未登录
            alert("请先登录！" + result.msg);
            // 并写入缓存
            saveLocal(TEMPORARY_BAN_URL);
            // 跳转登陆界面
            window.location.href = getAppInfo().hosts.appLoginHost;
            return;
        }
        // 递归回调
        if (success !== undefined)
            success(result);
    }
    httpGo(url, data, successLogin, method, error);
}

/**
 * 通用的 Http 请求 发送json参数的请求
 * @param url 地址，可以是相对地址
 * @param json 数据，可以是任意对象 会主动序列化;
 * @param success 请求成功后的回调函数，默认为空
 * @param method  请求方式，默认GET，形式为”POST/GET/“  --可空
 * @param error   错误的回调，默认会提升网络错误  --可空
 */
function httpJsonGo(url, json, success, method, error) {
    // 若success 为 null
    if (success === null || success === undefined) {
        success = function () {
        };
    }
    // 若method为null 默认POST
    if (method === undefined || method === null) {
        method = "POST";
    }
    // 若 error为null
    if (error === undefined || error === null) {
        error = function () {
            alert("网络错误！请检查网络！");
        };
    }
    if (json === null || json === undefined) {
        json = {};
    }
    // 发送json请求
    $.ajax({
        type: method,
        url: url,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        success: success,
        error: error
    });
}

/**
 * 通用的 Http 请求 仅发送普通数据参数
 * @param url 地址，可以是相对地址
 * @param data 数据，形式为 map 键值对{a:"",b:""};
 * @param success 请求成功后的回调函数，默认为空
 * @param method  请求方式，默认GET，形式为”POST/GET/“  --可空
 * @param error   错误的回调，默认会提升网络错误  --可空
 */
function httpGo(url, data, success, method, error) {
    // 若success 为 null
    if (success === null || success === undefined) {
        success = function () {
        };
    }
    // 若method为null 默认GET
    if (method === undefined || method === null) {
        method = "GET";
    }
    // 若 error为null
    if (error === undefined || error === null) {
        error = function () {
            alert("网络错误！请检查网络！");
        };
    }
    // 发送请求
    $.ajax({
        type: method,
        url: url,
        data: data,
        success: success,
        error: error
    });
}