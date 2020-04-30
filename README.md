## 代码自动生成工具
自动生成代码，不用老是手动添加所有的基础代码，节约时间去学习其他东西。

### 简单测试：
1. 添加test.sql文件到数据库
2. 直接运行MysqlGenerator 生成代码到当前项目，通过springboot：run启动项目
3. [访问swagger-ui](http://127.0.0.1:8082/swagger-ui.html)访问tb-user-controller中的接口，其他接口有点问题没处理

### code-generate-baomidou：`常用`
我一般后端就用它自动生成基础代码。配置springboot-security框架完成后端服务。
* [MyBatis-Plus参考文档](https://mybatis.plus/)
* [MyBatis-Plus的官方示例github](https://github.com/baomidou/mybatis-plus-samples.git)

### code-generage-renren ：
* 模仿人人项目：https://www.renren.io/

### 目录、文件介绍
* config目录 ：常用配置文件参考，自动生成项目后可以参考或复制使用
* vo目录：项目生成后需要的vo对象，可以copy到新项目里面
  * AppUsersVo、AppSearchUsersListVo这个组合主要是在swagger返回文档中生成这个类型的数据：[{}...],
    集合里面放对象
* application.yml：可以当成数据库配置参考
* MybatisPlusConfig：这个主要是用来配置mybaits的扫描路径

### 自动生成代码改动
在resources\templates目录下 我用的是vilocity引擎， 例子给的使用framework.
我根据自己的需求改了一下controller和entity，具体需求根据自己需要调整