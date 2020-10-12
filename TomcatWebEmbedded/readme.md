# 嵌入式tomcat 开发
- 启动Tomcat java.lang.NoClassDefFoundError异常解决方法
错误: 无法初始化主类 Main

原因: java.lang.NoClassDefFoundError: org/apache/catalina/WebResourceRoot

问题产生的原因：廖大佬用的eclipse，我们用的IDEA，我们在IDEA中，maven配置<scope>provided</scope>，就告诉了IDEA程序会在运行的时候提供这个依赖，但是实际上却并没有提供这个依赖。

## scope 说明
1.test范围是指测试范围有效,在编译和打包时都不会使用这个依赖
2.compile范围是指编译范围内有效,在编译和打包时都会将依赖存储进去
3.provided依赖,在编译和测试过程中有效,最后生成的war包时不会加入 例如:
   servlet-api,因为servlet-api  tomcat服务器已经存在了,如果再打包会冲突
4.runtime在运行时候依赖,在编译时候不依赖
默认依赖范围是compile