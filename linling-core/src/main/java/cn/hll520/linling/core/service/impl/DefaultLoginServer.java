package cn.hll520.linling.core.service.impl;

import cn.hll520.linling.core.object.safety.PromiseBase;
import cn.hll520.linling.core.object.safety.RoleBase;
import cn.hll520.linling.core.object.safety.UserBase;
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
    public UserBase login(UserBase user) {
        if ("admin".equals(user.getUsername())) {
            user.setUid(0);
            user.setPassword("admin");
        }
        return user;
    }

    @Override
    public List<RoleBase> checkRole(UserBase user) {
        if (user == null) {
            return null;
        }
        List<RoleBase> roles = new ArrayList<>();
        if ("admin".equals(user.getUsername()) && 0 == user.getUid()) {
            roles.add(RoleBase.builder().rid(0).roleName("admin").roleCode("admin")
                    .createTime(new Date()).build());
        }
        return roles;
    }

    @Override
    public List<PromiseBase> checkPromise(RoleBase role) {
        if (role == null) {
            return null;
        }
        List<PromiseBase> promises = new ArrayList<>();
        if ("admin".equals(role.getRoleCode())) {
            promises.add(PromiseBase.builder().pid(0).promiseName("admin")
                    .promiseCode("all").createTime(new Date()).build());
        }
        return promises;
    }

    @Override
    public List<PromiseBase> checkPromise(UserBase user) {
        if (user == null) {
            return null;
        }
        List<PromiseBase> promises = new ArrayList<>();
        if ("admin".equals(user.getUsername())) {
            promises.add(PromiseBase.builder().pid(0).promiseName("admin")
                    .promiseCode("all").createTime(new Date()).build());
        }
        return promises;
    }
}
