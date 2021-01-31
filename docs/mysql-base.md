#### 1. jdbc

##### 1.1 maven依赖

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.23</version>
</dependency>
```

##### 1.2 驱动加载

```java
Class.forName("com.mysql.jdbc.Driver");
```

##### 1.3 获取链接

```java
Connection conn = DriverManager.getConnection("host", "user", "password");
```

##### 1.4 执行sql

```java
PreparedStatement ps = conn.prepareStatement(sql);
ps.execute();
```

##### 1,5 关闭连接

```java
conn.close();
```

