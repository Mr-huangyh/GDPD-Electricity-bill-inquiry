# GDPD-Electricity-bill-inquiry（广东理工学院校园电费查询）
## 开端
> 校园的养老生活，实在太无聊了，然后最近学校的查电费公众号坏了，顺便也开始了解下（爬取）大数据怎么玩~
## 问题
* 发现的问题
> 1.由于编码问题导致出现的 "?" (不能清除)  
  2.未连接数据库...
* 解决的问题
> 1.Post(http)  
  2.拆分数据
### Post（http）
1.从校园网进入查询电费系统网址（http://dian.gdlgxy.com/）
2.发现查询需要先选择栋数然后再选择楼层，最后还要输入一遍完整的宿舍号，才能查询。
![图片1](https://raw.githubusercontent.com/Mr-huangyh/GDPD-Electricity-bill-inquiry/master/Img/1.png)
3.抓包发现他会访问（http://dian.gdlgxy.com/RoomSelect/GetRoomInfo?Apartid="不知名代码"&Roomname="宿舍号"）返回一个Html代码，其中就包含了宿舍号，已经使用的电量，剩余电量，抄表的时间。
![图片2](https://raw.githubusercontent.com/Mr-huangyh/GDPD-Electricity-bill-inquiry/master/Img/2.jpg)
4.尝试使用 "10101" 进行测试，翻了下html代码，发现不知名代码就是选择的栋数后下拉框返回给Js代码，-记录栋数与其相符的代码
![图片3](https://raw.githubusercontent.com/Mr-huangyh/GDPD-Electricity-bill-inquiry/master/Img/3.jpg)
5.然后使用Post方法，取返回数据。
### 拆分数据
>其实这个没什么好讲的，从post取回数据，将编码转为UTF-8编码[解决乱码问题]，因为他们都被包含在"<"label class="infolab">"和"<"/label">"之间，我使用的是字节分割成数组，取单数数组提取信息，可以使用正则表达式去替换，分割数组比较傻瓜就用了分割数组，如果对速度要求快，可以自己改。
## PS
* 已经开源，如做任何其他用途，均与本人无关。
