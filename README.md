代码生成工具
===============================
###介绍
		项目中使用到了jfinal框架，前端页面使用Jquery+bootstrap
		为了方便开发，使用freemarker生成对应对象的CURD代码，包括后台和前台，方便验证原型
		目前只支持从数据库反向，没有做通过URI模型的。
###使用
	运行app.java，即可在项目dest目录下生成对应代码
	
###其他
	数据库建立请全部使用小写，表使用自增id字段为主键（jfinal要求）
	生成的代码，能直接运行在 [jjb-frame](https://github.com/yueric/jjb-frame) 框架下面
