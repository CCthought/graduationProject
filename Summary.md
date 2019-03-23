<span style="color:red">红色</span>

## 1 http://www.bitbug.net

在线将图片转换成favicon.ico



## 2 from submits but reports error

the code is as follows;

```javascript
<form action="http://localhost:8080/register" method="post" enctype="application/x-www-form-urlencoded">
```

```java
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ActionResponse register(@RequestBody  User user) {
```

The reason:

​	①@RequestBody is used to handle application/json and application/xml,**disposing of @RequestBody,**

the app runs well

①what is enctype？

​	form的enctype属性规定在发送表单数据之前如何对其进行编码。

```
application/x-www-form-urlencoded：在发送前编码所有字符（默认） key-value
multipart/form-data：不对字符编码。在使用包含文件上传控件的表单时，必须使用该值。
text/plain：空格转换为 "+" 加号，但不对特殊字符编码。
```

​	<span style="color:red">特别注意，在form中没有application/json，因为enctype是指定数据的编码方式，而application/json是数据的传输格式，所以后台不能显示的用@RequestBody
</span>

![1550560440066](MD_IMAGES\1550560440066.png)



## 3 paging

①

the Front will pass two parameters `Integer pageSize, Integer currentPage`

②

通常，我们Dao和Service 的 method should be one-to-one correspondence,but we use paging,

the situation is different

because 分页在xml中 根本 没有办法 返回 有效的值



## 4 Json

`{"name":"adaiadaiadai","account": "abc"}` right

`{'name':"adaiadaiadai",'account': "abc"}`error

we must use `""` instead of `''` in Json



## 5 the return of mybatis sql

select : resultTpy resultMap

insert: 

​		success: 1

​		failure: 0 

​		exception: throw some relative exceptions

update,delete:

​		success:the effect of rows

​		failure: 0 （也可以理解成 没有更新和删除任何的行 如 where id = 1 在数据库中没有这条数据）

​		exception: throw some relative exceptions

```
如果删有外键约束的会报错
 `FK_test` FOREIGN KEY (`name`) REFERENCES `t_user` (`name`)
 
 this blog：https://blog.csdn.net/qq_24691413/article/details/83895574
```



## 6 the difference between err and out

```java
    1. public class TestCodeSeg  
    2. {  
    3.     static  
    4.     {  
    5.         System.out.println("1");  
    6.     }  
    7.     {  
    8.         System.out.println("2");  
    9.     }  
    10.     public TestCodeSeg()  
    11.     {  
    12.         System.err.println("3");  
    13.     }  
    14.     public static void main(String[] args)  
    15.     {  
    16.         new TestCodeSeg();  
    17.     }  
    18. }
```

the result：1，2顺序不变，3输出不定位置不定。

原因：System.out.println输出有缓存，System.err.println是立即输出，可能在输出1或2，还没有输出换行时输出3。



## 7 the difference of Tomcat/apache/nginx 

参考知乎：<a href="https://www.zhihu.com/question/32212996">tomcat 与 nginx，apache的区别是什么？</a>



## 8 一定要注重基础知识一定要注重基础知识一定要注重基础知识一定要注重基础知识一定要注重基础知识一定要注重基础知识

