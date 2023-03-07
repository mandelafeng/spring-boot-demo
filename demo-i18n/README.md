1. 配置文件
```
spring:
  basename: i18n/messages
  encoding: UTF-8
```
2. 在resource文件夹下新建
- messages_en_US.properties
- messages_zh_CN.properties
- messages.properties

创建 messages.properties 后 spring 会自动创建一个资源包 messages 文件夹，此文件夹不需要手动创建。
3. 配置拦截器并注册。
4. 使用
# todo
- [ ] 语言文件内key的设计
