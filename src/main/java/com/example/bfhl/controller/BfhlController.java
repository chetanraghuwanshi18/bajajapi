package com.example.bfhl.controller;

import com.example.bfhl.dto.BfhlRequestDTO;
import com.example.bfhl.dto.BfhlResponseDTO;
import com.example.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<BfhlResponseDTO> processData(@Valid @RequestBody BfhlRequestDTO requestDTO) {
        BfhlResponseDTO response = bfhlService.processData(requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
