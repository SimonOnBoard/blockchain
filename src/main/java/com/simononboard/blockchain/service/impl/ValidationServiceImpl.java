package com.simononboard.blockchain.service.impl;

import com.simononboard.blockchain.dao.model.Block;
import com.simononboard.blockchain.dao.repository.BlockRepository;
import com.simononboard.blockchain.service.interfaces.HashService;
import com.simononboard.blockchain.service.interfaces.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
    private final BlockRepository blockRepository;
    private final HashService hashService;

    @Override
    @Transactional
    public void validateChain() {
        List<Block> blockList = blockRepository.findAll(Sort.by(Sort.Direction.ASC, "index"));
        if(blockList.isEmpty()) throw new IllegalStateException("Requested validation for empty chain");
        byte[] prevHash = hashService.calculateHash(blockList.get(0), blockList.get(0).getDataSign());
        verifySign(prevHash, blockList.get(0).getSign());
        for (int i = 1; i < blockList.size(); i++) {
            if (!Arrays.equals(prevHash, blockList.get(i).getPrevHash())) {
                throw new IllegalStateException("Invalid sign");
            }

            prevHash = hashService.calculateHash(blockList.get(i), blockList.get(i).getDataSign());

            verifySign(prevHash, blockList.get(i).getSign());
        }
    }

    private void verifySign(byte[] prevHash, byte[] sign) {
        try {
            if (!hashService.verifyRSAPSSSignature(prevHash, sign)) {
                throw new IllegalStateException("Invalid sign");
            }
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
