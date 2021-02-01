LinLing Core is a SpringBoot framework that have many modules and complete SQL monitor and API document

**设置linling.host可以配置相应的映射，同时`app-login-host`用于配置真实的登录地址**

**AppInfo将返回一个MAP其中infos为appInfo,hosts为Host**

**全量配置见Core中的application-test.yaml**

# 引用和打包

## 1.引用

在引用项目中的pom文件加入如下内容

1、对Linling的父项目依赖

```xml

<parent>
    <artifactId>linling</artifactId>
    <groupId>cn.hll520.linling</groupId>
    <version>1.2.0</version>
</parent>
```

2、引入需要的包(以Core为例)

```xml

<dependencies>
    <!-- 核心 -->
    <dependency>
        <groupId>cn.hll520.linling</groupId>
        <artifactId>linling-core</artifactId>
        <version>1.2.0</version>
    </dependency>
</dependencies>
```

## 2.打包

在Pom文件中加入如下构建插件，点击package进行项目打包后的包才能直接运行，否则会报找不到清单文件错误

```xml
 <!-- 构建 -->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <!-- 编译lombok -->
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

# 使用方法

## 1.启动类

务必加上以下三个注解，并按`{}`中的格式加入当前项目/模块包路径，使得SpringIOC容器可以扫描到Linling的配置类服务

```java

@SpringBootApplication(scanBasePackages = {"cn.hll520.linling"})
@ComponentScan({"cn.hll520.linling"})
@MapperScan({"cn.hll520.linling.*.mapper"})
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
```

## 2.登录验证

实现`LoginServer` 接口，并加上`@Primary`注解，否则会和默认的实现类冲突

```java
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class DefaultLoginServer implements LoginServer {

}
```

## 3.Swagger配置

在`linling.core.swagger.base-package-path`中设置需要扫描的包路径即可

## 4.shiro 过滤

在`linling.core.safety.filter`中进行配置,为`List<Filter>` 只有 URL 和 ROLE 两个参数，按顺序配置

```yaml
linling:
  core:
    safety:
      filter:
        - url: /unok
          rule: user
        - url: /ok
          rule: anon
```

或实现 `IFilterUrlServer` 接口 并加上`@Primary`注解也可

```java

@Primary
@Service
public class FilterUrlServer implements IFilterUrlServer {
    @Override
    public Map<String, String> filterUrl(Map<String, String> filter, ShiroInfoValue value) {
        /* code */
    }
        return filter
}
}
```

# Manage菜单

## 1.yaml

可以使用yaml进行如下配置动态加载菜单

```yaml
      # 头部导航
      navbar:
        - name: LinLing
          url: http://hll520.cn
        - name: 百度
          url: http://baidu.com/
        - name: 360
          url: http://so.com
          target: _bank
        - name: API
          url: /api
        - name: SQL监管
          url: /sql
      # 左侧导航
      menu:
        # 一个组
        - name: linling
          menus:
            - name: Core
              url: /info
            - name: Manage
              url: /manage
        - name: video
          menus:
            - name: BILIBILI
              url: http://bilibili.com
            - name: YOUKU
              url: http://youku.com
```

## 2. 接口

可以实现`IManageServer`接口,并完成相应功能，需要加上`@primary`直接

```java
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ManageServer implements IManageServer {

    @Override
    public List<MenuGroup> leftMenu(List<MenuGroup> menuGroups) {
        return menuGroups;
    }

    @Override
    public List<Menu> topMenu(List<Menu> menus) {
        return menus;
    }
}

```

# 待解决

## 1.Core问题

1. 管理后台的响应式页面
2. 可以在配置文件中继续鉴权

## 2.待完成模块

1. Log -- 日志
2. Media -- 媒体
