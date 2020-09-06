[toc]

# Certificate

## 对称加密算法

* DES

> 数据加密标准（英语：Data Encryption Standard，缩写为 DES）是一种对称密钥加密块密码算法，
>1976年被美国联邦政府的国家标准局确定为联邦资料处理标准（FIPS），随后在国际上广泛流传开来。它基于使用56位密钥的对称算法。
>这个算法因为包含一些机密设计元素，相对短的密钥长度以及怀疑内含美国国家安全局（NSA）的后门而在开始时有争议，
>DES因此受到了强烈的学院派式的审查，并以此推动了现代的块密码及其密码分析的发展。

>DES现在已经不是一种安全的加密方法，主要因为它使用的56位密钥过短。1999年1月，distributed.net与电子前哨基金会合作，
>在22小时15分钟内即公开破解了一个DES密钥。也有一些分析报告提出了该算法的理论上的弱点，虽然在实际中难以应用。为了提供实用所需的安全性，
>可以使用DES的派生算法3DES来进行加密，虽然3DES也存在理论上的攻击方法。
>DES标准和3DES标准已逐渐被高级加密标准（AES）所取代。另外，DES已经不再作为国家标准科技协会（前国家标准局）的一个标准。

    * 3DES
* AES

>高级加密标准（英语：Advanced Encryption Standard，缩写：AES），在密码学中又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。
>这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。经过五年的甄选流程，高级加密标准由美国国家标准与技术研究院（NIST）
>于2001年11月26日发布于FIPS PUB 197，并在2002年5月26日成为有效的标准。现在，高级加密标准已然成为对称密钥加密中最流行的算法之一。

* PBE
> (Password-based Encryption Standard) 基于口令加密
>
>

* IDEA

## 非对称加密算法
* DH

> 密钥交换

## ssl加解密 
## 证书加密

### 生成密钥库
```
keytool -genkeypair -keyalg RSA -keysize 2048 -sigalg SHA1withRSA -validity 3650 -alias www.jikexueyuan.com -keystore jikexueyuan.keystore
```

### 生成证书请求
```
keytool -certreq -alias www.jikexueyuan.com -keystore jikexueyuan.keystore -file jikexueyuan.csr -v
```

### 自签名证书
```
keytool -exportcert -alias www.jikexueyuan.com -keystore jikexueyuan.keystore -file jikexueyuan.cer
```

[证书][1]

[代码][2]

## Base编码方式

- jdk实现

- commons-codec实现

- bouncy-casytle实现

[代码][3]

## 消息摘要算法

### MD(Message Digest)
1. MD5
2. MD家族(128)
	- MD2、MD4

|算法|摘要长度|实现方|
-|-|-
|MD2|128|jdk
|MD4|128|bouncy Castle|
|MD5|128|Jdk|


### SHA(Secure Hash Algorithm)

- 安全散列算法
- 固定长度摘要
- SHA-1、SHA-2(SHA-224、SHA-256、SHA-384、SHA512)

|算法 |摘要长度|实现方|
-|-|-
SHA-1|160|JDK
SHA-224|224|Bouncy Castle
SHA-256|256|JDK
 SHA-384|384|JDK
 SHA-512|512|JDK
 

### MAC(Message Authentication Code)


  
  [1]: https://github.com/admxj/Certificate/tree/master/ssl
  [2]: https://github.com/admxj/Certificate/tree/master/src/certificate
  [3]: https://github.com/admxj/Certificate/tree/master/src/com/admxj/security/base64/AdmxjBase64.java