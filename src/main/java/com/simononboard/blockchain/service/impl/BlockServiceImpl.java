package com.simononboard.blockchain.service.impl;

import com.simononboard.blockchain.common.exception.NotFoundException;
import com.simononboard.blockchain.dao.model.Block;
import com.simononboard.blockchain.dao.repository.BlockRepository;
import com.simononboard.blockchain.dao.repository.HashContainerRepository;
import com.simononboard.blockchain.model.BlockInfo;
import com.simononboard.blockchain.model.mapper.BlockMapper;
import com.simononboard.blockchain.service.interfaces.BlockService;
import com.simononboard.blockchain.service.interfaces.HashService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {
    private final BlockRepository blockRepository;
    private final HashContainerRepository hashContainerRepository;
    private final HashService hashService;
    private final BlockMapper blockMapper;

    @Override
    public synchronized BlockInfo createBlock(List<String> text) {
        var hashContainerInfo = hashContainerRepository.findAll().get(0);
        Block block = Block.builder()
                .data(text)
                .index(hashContainerInfo.getCurrentNum())
                .prevHash(hashContainerInfo.getCurrentHash())
                .build();
        try {
            var signForData = hashService.generateSignForData(block.getData());
            hashContainerInfo.setCurrentHash(hashService.calculateHash(block, signForData));
            hashContainerInfo.setCurrentNum(block.getIndex() + 1);
            hashContainerRepository.save(hashContainerInfo);
            block.setDataSign(signForData);
            block.setSign(hashService.generateSignForBlock(hashContainerInfo.getCurrentHash()));
            block = blockRepository.save(block);
            return blockMapper.mapFromBlockEntity(block);
        } catch (NoSuchProviderException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Transactional
    public BlockInfo getBlockInfo(int index) {
        var block = blockRepository.findBlockByIndex(index)
                .orElseThrow(() -> new NotFoundException("Блок с таким индексом не найден"));
        return blockMapper.mapFromBlockEntity(block);
    }
}
