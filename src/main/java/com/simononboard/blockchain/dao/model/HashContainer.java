package com.simononboard.blockchain.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HashContainer {
    @Id
    private Long id;

    @Lob
    private byte[] currentHash;

    private int currentNum;

}
