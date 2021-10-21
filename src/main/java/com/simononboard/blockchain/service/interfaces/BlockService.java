package com.simononboard.blockchain.service.interfaces;

import com.simononboard.blockchain.model.BlockInfo;

import java.util.List;

public interface BlockService {
    BlockInfo createBlock(List<String> text);

    BlockInfo getBlockInfo(int index);
}
