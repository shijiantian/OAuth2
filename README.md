本项目是springboot+gradle+oauth2的一个项目，用于学习归纳和总结。<br>
默认管理员帐号:daming，默认密码:123456
ClientID:myClient,secret:mypassword

swagger-ui页面授权说明:
点击swagger-ui页面右上角Authorize按钮，填写用户名密码、在Setup client authentication下方选择Basic auth并填写CliendId和secret;之后选择scope点击下方的Authorize按钮完成授权。

前端代码地址在<a href="https://github.com/shijiantian/webClient
docker">这里</a><br>镜像运行请查看<a href="https://github.com/shijiantian/dockerFiles/tree/master/webserver">这里</a>

分支1.0 集成spring security,并在内存中创建用户admin。

分支1.1 集成mysql,通过mysql管理用户和角色。

分支1.2 在数据库中管理角色权限

分支1.3 集成swagger,swagger访问页面:<a href="http://127.0.0.1:8083/swagger-ui.html">http://127.0.0.1:8083/swagger-ui.html</a>

分支1.4 集成redis和OAuth2.0密码模式,通过redis存储token

分支2.0 开发了一个结合前端使用的水电收支明细查看功能，记录水表和电表数值计算每月消费并以图表展示<br>
	添加了一个大文件多线程上传功能，以及水电数据的excel导入与导出<br>
