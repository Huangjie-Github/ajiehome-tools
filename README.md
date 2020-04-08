# 阿杰之家-工具包

**功能**
- API自动生成
- Token生成与验证注解
- request请求中body数据的JSON返回
- 邮箱验证码发送
- 文件的MD5计算

**说明**
- 使用工具包需要在启动类继承BaseBeanUtils抽象类，内实现的是把所有的需要类，注解给IOC容器，方便在后面自动注入
- 使用API生成时需要在当前的启动类上使用swagger2的使用注解（@EnableSwagger2），访问地址：http://ip:port/swagger-ui.html
- Token签发加密是HS256，需要改实体类，因为密钥和签发人与实体类有关，使用时只需要添加@AllowToken注解即可，使用的AOP加强方法验证
- 多module打包方式，作为记录一下
- body中的JSON数据返回使用的IO读取操作，记录一下
- 内所有的BO作为数据传递，返回给用户的为VO，数据库操作的无尾缀
- 邮箱验证码发送，需要开启相关邮箱的IMAP/SMTP POP3/SMTP服务，然后给spring注入mail的指定数据源(properties)
- 传入文件的File对象，计算出文件的MD5值，一般用来比对文件是否一致

**第三方JAR**
- [JWT-API](https://javadoc.io/doc/com.auth0/java-jwt/latest/index.html)
- [FastJson](https://github.com/alibaba/fastjson)
- [SpringFox-Swagger](http://springfox.github.io/springfox/docs/current/#swagger-1-2-vs-swagger-2-0)