## 1、Mybatis获取参数值的两种方式

>两种获取参数值的方式：**${}** 和 **#{}**
>
>${}的本质是字符串拼接，会造成SQL注入问题，在使用时需要注意添加单引号，例如：'${userName}'
>
>#{}的本质是占位符赋值，避免SQL注入问题，在填充占位符 (?) 时会自动带上单引号

#### mybatis获取参数的情况：

1. 若 mapper 接口方法的参数为单个的字面量类型，此时可以通过 #{} 和 ${} 以任意内容获取参数值（mybatis3.5版本之后）

2. 若 mapper 接口方法的参数为多个的字面量类型，此时 mybatis 会将多个参数放入按以下两种方式存储在map集合中

   1. 以 arg0，arg1...为 key ，以参数为 value 存储
   2. 以 param1，param2...为 key，以参数为 value 存储 

   - **因此只需通过 #{} 和 ${} 访问map集合的 key ，就可以获取相应的参数值**

3. 若 mapper 接口方法的参数为map集合类型的参数，**只通过 #{} 和 ${} 访问map集合的 key ，就可以获取相应的参数值**

4. 若 mapper 接口方法的参数为实体类类型的参数，**只需要通过 #{} 和 ${} 访问实体类中的属性名，就可以获取相应属性值**

   - 实体类中的属性名与实体类中的成员变量无关，只与实体类中的 getter 和 setter 方法有关
   - 当实体类没有成员变量，只有 getter 和 setter 方法，也是可以访问到实体类的属性

5. 可以在 mapper 接口方法的参数上设置 @Param 注解，此时 mybatis 会将这些参数按以下两种方式存储在map集合中

   1. 以 @Param 注解的 value 属性值为 key ，以参数为 value 存储
   2. 以 param1，param2...为 key，以参数为 value 存储 

   - **只需通过 #{} 和 ${} 访问map集合的 key ，就可以获取相应的参数值**

## 2、Mybatis的各种查询

1. 查询单个实体类对象
   - 若 SQL 语句查询的结果为多条时，则不能以实体类类型作为 mapper 接口方法的返回值类型，否则会抛出异常 TooManyResultsException
   - 若 SQL 语句查询的结果为一条时，则可以使用实体类类型或者 List 集合类型作为mapper接口方法方返回值类型
2. 查询一个 List 集合
3. 查询单个数据值（例：count(*) ....）
   - ResultType 可以使用类型别名
4. 查询一条数据为 map 集合
   - ResultType = map
   - 查询出来的 map 集合会以字段名为 key ，以字段值为value
5. 查询多条数据为 map 集合
   - ResultType = map
   - 一个 map 只能对应一条查询出来的记录，若需要查询多条数据为 map 集合可以通过以下两种方式
     1. 将 mapper 接口方法的返回值类型设置为泛型是 map 的 list 集合，例：List<Map<String,Object>>
     2. 在 mapper 接口方法中使用 @MapKey 注解，可以将查询的多条 map 集合数据放入一个 map 集合中，当时需要将查询的某个字段的值作为该大 map 的 key，例如：@MapKey('id')，则会以字段 id 的值作为大 map 的 key

## 3、MyBatis 中自带的常用的类型别名

查看地址：[配置_MyBatis中文网](https://mybatis.net.cn/configuration.html#typeAliases)

## 4、 特殊 SQL 的执行

1. MyBatis 实现模糊查询，使用 mybatis 的三种 SQL 写法

   1. 使用 ${} 进行字串拼接，实现模糊查询

      ```sql
      select * from tableName where columnName like '%${query}%'
      ```

   2. 使用 concat() 函数拼接 #{} 占位符，也可实现模糊查询

      ```sql
      select * from tableName where columnName like concat('%',#{query},'%')
      ```

   3. 使用 "" 拼接 #{} 实现模糊查询

      ```sql
      select * from tableName where columnName like "%"#{query}"%"
      ```

2. MyBatis 实现批量删除

   ```sql
   delete from tableName where id in(${ids})  #ids 的形式为 1,2,3,.. 的字串
   ```

3. MyBatis 动态设置表名，使用 ${}

   ```sql
   select * from ${tableName}
   ```

4. 添加功能——获取添加成功后自增的主键

   ```xml
       <!--
           添加用户 并获取自增的主键
           useGeneratedKeys: 表示当前添加功能是否使用自增的主键
           keyProperty: 将添加成功后自增的主键赋值给实体类参数的属性
       -->
       <insert id="insertUser" useGeneratedKeys="true" keyProperty="id">
           insert into user1 values(null,#{name},#{pwd})
       </insert>
   ```

## 5、自定义映射 resultMap

1. 字段名与实体类属性名不一致的情况，处理映射的方式如下

   1. 可以在 SQL 中给字段设置和属性名一致的别名

   2. 若字段名使用 _ 命名方式，属性使用驼峰命名方式，则可以在 MyBatis 核心配置文件中添加以下配置，可以实现自动将 _ 映射为驼峰

      ```xml
          <settings>
              <setting name="mapUnderscoreToCamelCase" value="true"/>
          </settings>
      ```

   3. 使用 resultMap 实现自定义映射 

      - id：设置唯一标识
      - type ：处理映射关系对应的实体类类型
      - id 标签：处理主键和实体类中属性的映射关系
      - result 标签：处理普通字段和实体类中属性的映射关系
      - column：字段名
      - property：实体类的属性名

      ```xml
          <select id="getUserById" resultMap="userMap">
              select * from user1 where id=#{userId}
          </select>
          <resultMap id="userMap" type="user">
              <id column="id" property="id"></id>
              <result column="name" property="name"></result>
              <result column="pwd" property="password"></result>
          </resultMap>
      ```

2. 多对一映射处理

   1. 级联方式处理

      ```xml
        <resultMap id="orderMap" type="order">
              <result column="ordertime" property="orderTime"></result>
              <result column="oid" property="id"></result>
              <result column="total" property="total"></result>
              
              <result column="uid" property="user.id"></result>
              <result column="name" property="user.name"></result>
              <result column="pwd" property="user.pwd"></result>
          </resultMap>
          
          <select id="queryOrder" resultMap="orderMap">
              SELECT *,o.`id` oid FROM user1 u,orders o WHERE o.`uid`=u.`id`
          </select>
      ```

   2. 使用 association 标签处理映射

      ```xml
          <resultMap id="orderMap" type="order">
              <id column="oid" property="id"></id>
              <result column="ordertime" property="orderTime"></result>
              <result column="total" property="total"></result>
              <!--
                  association: 处理多对一或一对一映射关系（处理实体类类型属性）
               -->
              <association property="user" javaType="user" >
                  <id column="uid" property="id"></id>
                  <result column="name" property="name"></result>
                  <result column="pwd" property="pwd"></result>
              </association>
          </resultMap>
      ```

   3. 分步查询

      ```xml
          <!--  第三种：分步查询  -->
          <resultMap id="queryOrderAndUserStepOneMap" type="order">
              <id column="id" property="id"></id>
              <result column="ordertime" property="orderTime"></result>
              <result column="total" property="total"></result>
              <!--
                  association: 处理多对一或一对一映射关系（处理实体类类型属性）
                  property: 设置需要映射的实体类
                  select：设置下一步 SQL 查询语句的唯一标识
                  column：将本步查询出来的字段作为下一步 SQL 查询的条件
               -->
              <association
                      property="user"
                      select="com.lhk.mapper.UserMapper.queryOrderAndUserStepTwo"
                      column="uid" >
              </association>
          </resultMap>
      
          <select id="queryOrderAndUserStepOne" resultMap="queryOrderAndUserStepOneMap">
              select * from orders
          </select>
      
      ```

      - 分步查询的优势：可以实现**延迟加载**（懒加载），（用到哪些数据，就只执行对应的SQL语句，按需加载）

        1. 全局配置延迟加载

           ```xml
                   <!-- 开启延迟加载 -->
                   <setting name="lazyLoadingEnabled" value="true"/>
                   <!-- 开启按需加载,默认为false -->
                   <setting name="aggressiveLazyLoading" value="false"/>
           ```

        2. 配置某个分布查询延迟加载

           - fetchType="lazy"（本分步查询开启延迟加载）
           - fetchType="eager"  （本分步查询开启立即加载）

           ```xml
           <association
           	property="user"
           	fetchType="lazy"
               select="com.lhk.mapper.UserMapper.queryOrderAndUserStepTwo"
           	column="uid" >
           </association>
           ```

3. 一对多映射处理

   

