1. 命名 xxx-spring-boot-starter
2. 引入依赖
```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
    </dependencies>
    <dependencyManagement>
        <!-- 我们是基于Springboot的应用 -->
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

```
3. 创建配置类及其它核心代码
4. 在resources目录下创建META-INF，并创建spring.factories
5. 在其中写入
```xml
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.jdkcb.mystarter.config.MyStarterAutoConfiguration

```
6. maven install到本地仓库
7. 在其它项目中引用
```xml
        <dependency>
            <groupId>com.cfhui</groupId>
            <artifactId>demo-spring-boot-starter</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```
