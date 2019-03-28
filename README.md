## 简介

基于**springboot+jpa+mysql**的**login+register**的demo

## 展示

没有用模板做反馈，用**postman**进行接口的测试。

在LoginDemoApplication实现了ApplicationRunner的run方法插入初始的一个用户数据：

```java
// init() 仅执行一次，给数据库插入（admin，123456）数据提供登录，第二次运行前请注释
    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }
```

**下面是接口测试结果：**

**login**：设定Content-Type为application/json，==Post(localhost:8080/login)==

**当Body的json数据：**

```json
{
	"username":"admin",
	"pwdHash":"123"
}
```

返回：

```json
username or password wrong!
```

**当Body的json数据：**

```json
{
	"username":"admin",
	"pwdHash":""
}
```

返回：

```json
username or password is Empty!
```

**当Body的json数据：**

```json
{
	"username":"admin",
	"pwdHash":"123456"
}
```

返回：

```json
login sucessful!
```

**register**：设定Content-Type为application/json，==Post(localhost:8080/register)==

**当Body的json数据：**

```json
{
	"username":"admin",
	"pwdHash":"test"
}
```

返回：

```json
username is exit!
```

**当Body的json数据：**

```json
{
	"username":"test",
	"pwdHash":"test"
}
```

返回：

```json
register sucessful!
```

## 注意事项

关注application.yml文件，设定对应的mysql信息

分层的话，主要分层：

1. model
2. repository
3. service
4. controller

