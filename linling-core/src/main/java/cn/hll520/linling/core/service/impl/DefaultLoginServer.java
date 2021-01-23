package cn.hll520.linling.core.service.impl;

import cn.hll520.linling.core.object.safety.Promise;
import cn.hll520.linling.core.object.safety.Role;
import cn.hll520.linling.core.object.safety.User;
import cn.hll520.linling.core.service.LoginServer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述： 默认登录实现类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-14:40
 * @since 2021-01-23-14:40
 */
@Service
public class DefaultLoginServer implements LoginServer {
    @Override
    public User login(User user) {
        if ("admin".equals(user.getUsername())) {
            user.setUid(0);
            user.setPassword("admin");
        }
        return user;
    }

    @Override
    public List<Role> checkRole(User user) {
        if (user == null) {
            return null;
        }
        List<Role> roles = new ArrayList<>();
        if ("admin".equals(user.getUsername()) && 0 == user.getUid()) {
            roles.add(Role.builder().rid(0).roleName("admin").roleCode("admin")
                    .createTime(new Date()).build());
        }
        return roles;
    }

    @Override
    public List<Promise> checkPromise(Role role) {
        if (role == null) {
            return null;
        }
        List<Promise> promises = new ArrayList<>();
        if ("admin".equals(role.getRoleCode())) {
            promises.add(Promise.builder().pid(0).promiseName("admin")
                    .promiseCode("all").createTime(new Date()).build());
        }
        return promises;
    }
}
