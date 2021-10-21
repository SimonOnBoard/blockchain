package com.simononboard.blockchain.dao.repository;

import com.simononboard.blockchain.dao.model.HashContainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashContainerRepository extends JpaRepository<HashContainer, Long> {
}
