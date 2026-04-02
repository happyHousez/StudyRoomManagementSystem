# 自习室管理系统 - 后台管理模块

## 项目简介

这是一个基于 Java EE 的自习室管理系统后台管理模块，采用 SSM (Spring + Spring MVC + MyBatis) 框架构建，提供自习室管理、用户管理、预约管理等核心功能。

## 技术栈

### 后端框架
- **Spring Framework** 5.2.8.RELEASE - 核心容器和事务管理
- **Spring MVC** 5.2.8.RELEASE - Web MVC 框架
- **MyBatis** 3.5.2 - ORM 持久层框架
- **MyBatis PageHelper** 5.1.10 - 分页插件

### 数据库
- **MySQL** 8.0.16
- **Druid** 1.1.20 - 数据库连接池

### 前端技术
- JSP + JSTL + Standard Tag Library
- Bootstrap CSS 框架
- jQuery & jQuery Validate
- AdminLTE UI 组件

### 其他依赖
- Jackson 2.9.x - JSON 处理
- Log4j 2.10.0 - 日志框架
- Servlet API 3.1.0
- Tomcat 7 Maven Plugin

## 项目结构

```
manager/
├── src/main/java/com/itheima/
│   ├── config/                    # Spring 配置文件
│   │   ├── SpringConfig.java      # Spring 主配置（事务、服务扫描）
│   │   ├── SpringMvcConfig.java   # Spring MVC 配置（拦截器、视图解析器）
│   │   ├── JdbcConfig.java        # 数据源配置
│   │   └── MyBatisConfig.java     # MyBatis 配置
│   ├── controller/                # 控制器层
│   │   ├── UserController.java    # 用户管理控制器
│   │   ├── RoomController.java    # 自习室管理控制器
│   │   └── AppointController.java # 预约记录控制器
│   ├── service/                   # 业务逻辑层
│   │   ├── UserService.java
│   │   ├── RoomService.java
│   │   ├── AppointService.java
│   │   └── impl/                  # 服务实现类
│   ├── mapper/                    # MyBatis Mapper 接口
│   │   ├── UserMapper.java
│   │   ├── RoomMapper.java
│   │   └── AppointMapper.java
│   ├── domain/                    # 实体类
│   │   ├── User.java
│   │   ├── Room.java
│   │   └── Appoint.java
│   └── interceptor/               # 拦截器
│       └── ResourcesInterceptor.java
├── src/main/resources/
│   ├── com/itheima/mapper/        # MyBatis XML 映射文件
│   │   ├── UserMapper.xml
│   │   ├── RoomMapper.xml
│   │   └── RecordMapper.xml
│   ├── jdbc.properties            # 数据库连接配置
│   ├── log4j.properties           # 日志配置
│   └── ignoreUrl.properties       # 免拦截 URL 配置
└── src/main/webapp/
    ├── admin/                     # 后台管理页面
    │   ├── index.jsp              # 首页
    │   ├── login.jsp              # 登录页
    │   ├── main.jsp               # 主界面
    │   ├── user.jsp               # 用户管理
    │   ├── rooms.jsp              # 自习室管理
    │   ├── rooms_new.jsp          # 新增自习室
    │   ├── room_modal.jsp         # 自习室模态框
    │   └── record.jsp             # 预约记录
    ├── css/                       # 样式文件
    ├── js/                        # JavaScript 文件
    └── WEB-INF/
        └── web.xml                # Web 应用配置
```

## 核心功能

### 1. 用户管理 (`UserController`)
- 用户登录/注销
- 用户信息增删改查
- 用户名和邮箱校验
- 分页查询用户列表
- 角色权限管理（管理员/普通用户）

### 2. 自习室管理 (`RoomController`)
- 自习室信息查询（热门自习室）
- 自习室新增/编辑
- 自习室预约功能
- 分页查询和条件搜索

### 3. 预约管理 (`AppointController`)
- 预约记录查询
- 预约信息管理
- 按用户和条件筛选预约记录

### 4. 拦截器 (`ResourcesInterceptor`)
- 登录验证
- URL 访问权限控制
- 静态资源放行

## 快速开始

### 环境要求
- JDK 18+
- Maven 3.6+
- MySQL 8.0+
- Tomcat 9+ (或使用 Maven Tomcat 插件)

### 安装步骤

1. **克隆项目**
```bash
git clone <repository-url>
cd manager
```

2. **配置数据库**
- 创建 MySQL 数据库 `study_room`
- 修改 `src/main/resources/jdbc.properties` 配置文件：
```properties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/study_room?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
jdbc.username=your_username
jdbc.password=your_password
```

3. **导入数据库表结构**
```sql
-- 创建用户表
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    password VARCHAR(100),
    email VARCHAR(100),
    role VARCHAR(20)
);

-- 创建自习室表
CREATE TABLE room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    location VARCHAR(200),
    appointment VARCHAR(50),
    appointTime DATETIME
);

-- 创建预约记录表
CREATE TABLE appoint (
    id INT PRIMARY KEY AUTO_INCREMENT,
    roomname VARCHAR(100),
    location VARCHAR(200),
    appointment VARCHAR(50),
    appointTime DATETIME
);
```

4. **编译项目**
```bash
mvn clean install
```

5. **运行项目**
```bash
# 使用 Tomcat Maven 插件运行
mvn tomcat7:run
```

访问地址：http://localhost:8080/manager

### 默认账户
根据项目配置，您需要自行初始化管理员账户数据。

## 开发说明

### 构建命令
```bash
# 清理编译
mvn clean

# 编译打包
mvn package

# 运行测试
mvn test

# 启动 Tomcat 服务器
mvn tomcat7:run

# 部署 WAR 包
mvn tomcat7:deploy
```

### 配置说明

#### Spring 配置
- **SpringConfig.java**: 启用事务管理，扫描 Service 层
- **SpringMvcConfig.java**: 配置视图解析器、拦截器、静态资源处理
- **JdbcConfig.java**: 配置 Druid 数据源
- **MyBatisConfig.java**: 配置 SqlSessionFactory 和分页插件

#### 拦截器配置
在 `ignoreUrl.properties` 中配置不需要拦截的 URL：
```properties
ignoreUrl=/user/login,/admin/login.jsp
```

## API 接口

### 用户相关
- `POST /user/login` - 用户登录
- `GET /user/logout` - 用户注销
- `POST /user/addUser` - 新增用户
- `POST /user/delUser` - 删除用户
- `POST /user/editUser` - 编辑用户
- `GET /user/search` - 查询用户列表
- `GET /user/findById` - 根据 ID 查询用户
- `GET /user/checkName` - 检查用户名是否存在
- `GET /user/checkEmail` - 检查邮箱是否存在

### 自习室相关
- `GET /room/selectHotRoom` - 查询热门自习室
- `GET /room/searchRooms` - 查询自习室列表
- `GET /room/findById` - 根据 ID 查询自习室
- `POST /room/borrowRoom` - 预约自习室
- `POST /room/editRoom` - 编辑自习室
- `POST /room/addRoom` - 新增自习室

### 预约记录相关
- `GET /appoint/searchAppoint` - 查询预约记录

## 常见问题

### 1. 数据库连接失败
检查 `jdbc.properties` 配置是否正确，确保 MySQL 服务已启动。

### 2. 中文乱码问题
项目已配置 `EncodingFilter` 处理字符编码，确保 Tomcat URI 编码设置为 UTF-8。

### 3. 静态资源无法访问
检查 `SpringMvcConfig.java` 中的静态资源配置和拦截器配置。

## 项目扩展建议

1. **安全性增强**
   - 添加密码加密（BCrypt）
   - 实现 CSRF 防护
   - 添加 XSS 过滤

2. **功能优化**
   - 添加 RESTful API 支持
   - 集成 Redis 缓存
   - 添加定时任务处理过期预约

3. **性能优化**
   - 数据库连接池调优
   - 添加慢查询日志
   - 实现懒加载

## 许可证

本项目仅供学习交流使用。

## 联系方式

如有问题，请通过 Issue 反馈。
