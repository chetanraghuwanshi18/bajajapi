package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequestDTO;
import com.example.bfhl.dto.BfhlResponseDTO;

public interface BfhlService {

    BfhlResponseDTO processData(BfhlRequestDTO requestDTO);
}
