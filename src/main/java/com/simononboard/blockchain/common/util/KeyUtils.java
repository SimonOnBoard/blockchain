package com.simononboard.blockchain.common.util;

import lombok.experimental.UtilityClass;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@UtilityClass
public class KeyUtils {
    private static final String algorithm = "SHA-256";

    private static final String keyAlgorithm = "RSA";

    private static final String signAlgorithm = "SHA256withRSAandMGF1";

    public static KeyPair loadKeys() throws Exception {

        byte[] publicKeyHex = Files.readAllBytes(Paths.get("public.key"));
        byte[] privateKeyHex = Files.readAllBytes(Paths.get("private.key"));

        PublicKey publicKey = convertArrayToPublicKey(Hex.decode(publicKeyHex), keyAlgorithm);
        PrivateKey privateKey = convertArrayToPrivateKey(Hex.decode(privateKeyHex), keyAlgorithm);

        KeyPair keyPair = new KeyPair(publicKey, privateKey);
        return keyPair;
    }


    public static PublicKey convertArrayToPublicKey(byte encoded[], String algorithm) throws Exception {
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

        return pubKey;
    }

    public static PrivateKey convertArrayToPrivateKey(byte encoded[], String algorithm) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);
        return priKey;
    }

}
