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



