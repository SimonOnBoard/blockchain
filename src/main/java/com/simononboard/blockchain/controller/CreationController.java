package com.simononboard.blockchain.controller;

import com.simononboard.blockchain.model.BlockInfo;
import com.simononboard.blockchain.service.interfaces.BlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.simononboard.blockchain.common.constants.ApiConstant.BLOCKS;

@RestController
@RequiredArgsConstructor
@Tag(name = "Block management / Управление блоком")
@RequestMapping(BLOCKS)
public class CreationController {
    private final BlockService blockService;

    @PostMapping
    @Operation(summary = "Создание блока")
    public ResponseEntity<BlockInfo> createBlock(@RequestParam(name = "text") List<String> data) {
        return ResponseEntity.ok(blockService.createBlock(data));
    }

    @GetMapping("/{index}")
    @Operation(summary = "Просмотр информации блока")
    public ResponseEntity<BlockInfo> getBlockInfo(@PathVariable("index") int index) {
        return ResponseEntity.ok(blockService.getBlockInfo(index));
    }

}
