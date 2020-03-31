# 阿杰之家-工具包

**功能**
- API自动生成
- Token生成与验证注解

**说明**
- 使用API生成时需要在当前的启动类上使用swagger2的使用注解（@EnableSwagger2），访问地址：http://地址/swagger-ui.html
- Token签发加密是HS256，需要改实体类，因为密钥和签发人与实体类有关

**第三方JAR**
- [JWT-API](https://javadoc.io/doc/com.auth0/java-jwt/latest/index.html)
- [FastJson](https://github.com/alibaba/fastjson)
- [SpringFox-Swagger](http://springfox.github.io/springfox/docs/current/#swagger-1-2-vs-swagger-2-0)