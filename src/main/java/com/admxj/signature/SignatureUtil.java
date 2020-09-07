package com.admxj.signature;

import lombok.Data;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * The interface Signature util.
 *
 * @author 项金
 * @version Id : SignatureUtil, v 0.1 2020/9/6 5:42 下午 项金 Exp $
 */
public interface SignatureUtil {

    @Data
    class SignatureKey {
        private PrivateKey privateKey;
        private PublicKey publicKey;

        /**
         * Instantiates a new Rsa key.
         *
         * @param privateKey the private key
         * @param publicKey  the public key
         */
        public SignatureKey(PrivateKey privateKey, PublicKey publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }
    }


    /**
     * 生成密钥对
     *
     * @return com.admxj.signature key
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public SignatureKey initKey() throws NoSuchAlgorithmException;

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte [ ]
     * @throws Exception the exception
     */
    public byte[] sign(byte[] data, byte[] privateKey) throws Exception;

    /**
     * 验签
     *
     * @param data      待验签数据
     * @param sign      签名
     * @param publicKey 公钥
     * @return boolean
     * @throws Exception the exception
     */
    public boolean verify(byte[] data, byte[] sign, byte[] publicKey) throws Exception;
}