
#### 请求报文结构

- 第一行为请求行，包含请求方法、URL（与请求首部Host组成完整的URL）、协议版本
- 接下来多行请求首部，每行为首部名和对应的值
- 一个空行分隔首部和请求体
- 请求体

```html
GET / HTTP/1.1
Host: www.baidu.com
Connection: keep-alive
sec-ch-ua: "Google Chrome";v="89", "Chromium";v="89", ";Not A Brand";v="99"
sec-ch-ua-mobile: ?0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: none
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Sec-Fetch-Dest: document
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
Cookie: BIDUPSID=?; PSTM=?; __yjs_duid=?; BDUSS=?; BDUSS_BFESS=?; ab_sr=?; H_PS_PSSID=?; BD_UPN=?; H_PS_645EC=?; BDORZ=?; BAIDUID=?
```

#### 响应报文结构

- 第一行包括协议版本、状态码及描述
- 多行响应首部
- 空行
- 响应体

```html
HTTP/1.1 302 Moved Temporarily
Location: https://www.baidu.com/?tn=02049043_30_pg&ch=11
```

#### HTTP请求方法

请求报文第一行第一个字段为请求方法

- GET:获取资源，无副作用
- HEAD:类似GET，但不返回响应报文体
- POST:用于提交数据，数据包含在请求体中，可能会创建新资源或修改现有资源
- PUT:向指定资源位置上传文件
- PATCH:对资源进行部分修改
- DELETE:删除资源
- OPTIONS:查询URL支持的方法
- CONNECT:用于将连接改为管道方式，通常用于SSL加密

#### GET和POST

- GET参数拼接在URL后，只能以ASCII码编码，请求参数受到URL长度限制，POST参数存放在请求体，支持标准字符集，不限制请求参数
- GET参数直接暴露在URL，更不安全，不能用来传递敏感数据
- GET方法不会改变服务器状态，是幂等的，而PUT方法可以上传数据改变服务器状态，不是幂等
- 在浏览器回退时，GET是无害的，而POST会再次提交请求
- GET请求会被浏览器主动缓存，而POST需要手动设置

#### HTTP状态码

- 1xx：请求已被服务器接收，正在处理
  - 100 Continue：正常继续
- 2xx：请求已被服务器接收成功
  - 200 OK：请求成功
  - 204 No Content：请求已处理，但返回的响应报文不含响应体
  - 206 Partial Content：客户端进行范围请求，响应体包含Content-Range指定的范围
- 3xx：重定向，需要后续操作才能完成请求
  - 301 Moved Permanently：永久重定向
  - 302 Found：临时重定向
  - 303 See Other：要求使用GET请求，类似302
  - 304 Not Modified：访问缓存
  - 307 Temporary Redirect ：临时重定向，不会改变POST方法
- 4xx：客户端错误
  - 400 Bad Request：报文中存在语法错误
  - 401 Unauthorized：请求需要认证，认证失败
  - 403 Forbidden：请求被拒绝
  - 404 Not Found：资源不存在
  - 405 Method Not Allowed：不支持该请求方法
- 5xx：服务端错误
  - 500 Internal Server Error：执行请求发生错误
  - 502 Bad Gateway：网关错误
  - 503 Service Unavailable：服务器无法处理请求，维护或超负载

#### HTTP首部

- Connection: keep-alive 默认支持持久连接
- Host 请求资源服务器
- Accept 可处理的类型
- Cookie 客户端缓存
- Location 客户端重定向至指定URI

#### Cookie和Session

HTTP是无状态协议，Cookie用于保存状态信息，Cookie是服务器发送到用户浏览器并保存在本地的数据，会在向同一服务器再次发起请求时带上。

Session存储在服务器，服务器通过将Session ID放入Set-Cookie字段发送给客户端，客户端请求时带上Session ID，服务器根据Session ID取出保存的Session。

当浏览器禁用Cookie时，只能使用Session，Session ID将作为URL的参数传递。

Cookie存在本地浏览器，是不安全的，可以将Cookie值进行加密，然后再服务端进行解析。

#### HTTPS

HTTP具有以下安全性问题：

- 使用明文通信，内容可能会被窃听
- 不验证通信双方的身份，通信双方可能遭遇伪装
- 无法验证明文的完整性，报文可能被篡改

HTTPS让HTTP先和SSL（安全套接字协议）通信，再由SSL和TCP通信，通过HTTPS可以加密（防窃听）、认证（防伪装）和完整性保护。

HTTPS采用对称和非对称混合加密，利用非对称加密传输对称密钥和验证签名，通信采用对称加密。

- 客户端向服务器发情请求，请求443端口，发送自己支持的加密算法
- 服务器将CA证书发送给客户端，CA证书包括证书机构、使用机构、公钥、有效期、签名算法、签名hash算法、指纹算法、指纹
- 客户端验证证书，使用该CA证书的公钥对其解密，取出CA证书中的公钥，生成随机数（对称密钥），使用签名hash算法获取握手hash值，使用私钥对握手信息和握手hash值加密，使用公钥对随机数进行加密，一起发送给服务端
- 服务端接收到加密的随机数，使用私钥进行解密得到对称密钥，使用对称密钥解密握手信息，与自己发送的对比验证，使用对称密钥加密后发回给客户端
- 客户端验证返回的握手信息完成握手，之后使用双方使用生成的对称密钥进行加密通信

