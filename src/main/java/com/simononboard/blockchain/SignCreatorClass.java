package com.simononboard.blockchain;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.*;

public class SignCreatorClass {
    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator keyPairGenerator = null;
        try (PrintWriter publicKeyWriter = new PrintWriter(new FileWriter("public.key"));
             PrintWriter privateKeyWriter = new PrintWriter(new FileWriter("private.key"));) {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            privateKeyWriter.write(new String(Hex.encode(keyPair.getPrivate().getEncoded())));
            publicKeyWriter.write(new String(Hex.encode(keyPair.getPublic().getEncoded())));
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new IllegalStateException(e);
        }
    }
}
