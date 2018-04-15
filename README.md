# Certificate

## ssl加解密 
##证书加密

###生成密钥库
```
keytool -genkeypair -keyalg RSA -keysize 2048 -sigalg SHA1withRSA -validity 3650 -alias www.jikexueyuan.com -keystore jikexueyuan.keystore
```

###生成证书
```
keytool -certreq -alias www.jikexueyuan.com -keystore jikexueyuan.keystore -file jikexueyuan.csr -v
```

###自签名证书
```
keytool -exportcert -alias www.jikexueyuan.com -keystore jikexueyuan.keystore -file jikexueyuan.cer
```

[证书][1]

[代码][2]

##Base加密方式

- jdk实现

- commons-codec实现

- bouncy-casytle实现

[代码][3]

###
  
  
  
  [1]: https://github.com/admxj/Certificate/tree/master/ssl
  [2]: https://github.com/admxj/Certificate/tree/master/src/certificate
  [3]: https://github.com/admxj/Certificate/tree/master/src/com/admxj/security/base64/AdmxjBase64.java