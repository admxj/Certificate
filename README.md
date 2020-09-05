# Certificate

## ssl加解密 
## 证书加密

### 生成密钥库
```
keytool -genkeypair -keyalg RSA -keysize 2048 -sigalg SHA1withRSA -validity 3650 -alias www.jikexueyuan.com -keystore jikexueyuan.keystore
```

### 生成证书
```
keytool -certreq -alias www.jikexueyuan.com -keystore jikexueyuan.keystore -file jikexueyuan.csr -v
```

### 自签名证书
```
keytool -exportcert -alias www.jikexueyuan.com -keystore jikexueyuan.keystore -file jikexueyuan.cer
```

[证书][1]

[代码][2]

## Base加密方式

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