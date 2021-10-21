package com.simononboard.blockchain.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class Block {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "com.simononboard.blockchain.dao.generator.CustomIdUUIDGenerator")
    @Column(name = "block_id")
    private UUID id;

    private int index;

    @ElementCollection
    @CollectionTable(
            name = "data",
            joinColumns = @JoinColumn(name = "block_id")
    )
    private List<String> data;

    @Lob
    private byte[] prevHash;

    @Lob
    private byte[] sign;

    @Lob
    private byte[] dataSign;

}
