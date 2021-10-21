package com.simononboard.blockchain.controller;

import com.simononboard.blockchain.service.interfaces.ValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.simononboard.blockchain.common.constants.ApiConstant.VALIDATIONS;

@RestController
@RequiredArgsConstructor
@Tag(name = "Validation management / Управление валидацией")
@RequestMapping(VALIDATIONS)
public class ValidationController {
    private final ValidationService validationService;

    @PostMapping
    @Operation(summary = "Валидация цепочки")
    public ResponseEntity<String> validateChain(){
        validationService.validateChain();
        return ResponseEntity.ok("Successful");
    }

}
