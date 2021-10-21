package com.simononboard.blockchain.model.mapper;

import com.simononboard.blockchain.dao.model.Block;
import com.simononboard.blockchain.model.BlockInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlockMapper {
    BlockInfo mapFromBlockEntity(Block entity);
}