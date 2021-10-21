package com.simononboard.blockchain.service.interfaces;

import com.simononboard.blockchain.dao.model.Block;

import java.security.*;
import java.util.List;

public interface HashService {
    byte[] calculateHash(Block block, byte[] signForData);

    byte[] generateSignForData(List<String> data) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;

    byte[] generateSignForBlock(byte[] currentHash) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException;

    boolean verifyRSAPSSSignature(byte[] input, byte[] encSignature)
            throws GeneralSecurityException;
}
