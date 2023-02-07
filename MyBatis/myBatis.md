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

   - 实体类中的属性名与实体类中的成员变量无关，至于实体类中的 getter 和 setter 方法有关
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

   