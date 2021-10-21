package com.simononboard.blockchain.service.impl;

import com.simononboard.blockchain.common.util.KeyUtils;
import com.simononboard.blockchain.dao.model.Block;
import com.simononboard.blockchain.service.interfaces.HashService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashServiceImpl implements HashService {
    @Value("SHA-256")
    private String algorithm;

    @Value("SHA256withRSAandMGF1")
    private String signAlgorithm;

    private KeyPair keyPair;

    @SneakyThrows
    @PostConstruct
    public void init() {
        keyPair = KeyUtils.loadKeys();
    }

    @Override
    public byte[] calculateHash(Block block, byte[] signForData) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm, "BC");
            var currentData = String.join("", block.getData()).getBytes(StandardCharsets.UTF_8);
            byte[] dataAndPrevHash = concatArrays(currentData, block.getPrevHash());
            byte[] result = concatArrays(dataAndPrevHash, signForData);
            return messageDigest.digest(result);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public byte[] generateSignForData(List<String> data) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        var dataToHash = String.join("", data).getBytes(StandardCharsets.UTF_8);
        return generateSignForBlock(dataToHash);
    }

    @Override
    public byte[] generateSignForBlock(byte[] data) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(signAlgorithm, "BC");

        signature.initSign(keyPair.getPrivate());

        signature.update(data);

        return signature.sign();
    }


    private byte[] concatArrays(byte[] a, byte[] b) {
        if (a == null) return b;
        if (b == null) return a;
        int len_a = a.length;
        int len_b = b.length;
        byte[] C = new byte[len_a + len_b];
        System.arraycopy(a, 0, C, 0, len_a);
        System.arraycopy(b, 0, C, len_a, len_b);
        return C;
    }

    @Override
    public boolean verifyRSAPSSSignature(byte[] input, byte[] encSignature)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(signAlgorithm, "BC");

        signature.initVerify(keyPair.getPublic());

        signature.update(input);

        return signature.verify(encSignature);
    }
}
