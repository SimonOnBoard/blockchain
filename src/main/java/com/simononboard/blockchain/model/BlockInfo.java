package com.simononboard.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlockInfo {
    private UUID id;
    private int index;
    private List<String> data;
    private byte[] prevHash;
    private byte[] sign;
}
