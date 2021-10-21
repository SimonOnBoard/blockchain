package com.simononboard.blockchain.dao.repository;

import com.simononboard.blockchain.dao.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BlockRepository extends JpaRepository<Block, UUID> {
    Optional<Block> findBlockByIndex(int index);
}
