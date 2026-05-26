package com.example.bfhl.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BfhlRequestDTO {

    @NotNull(message = "data field must not be null")
    private List<String> data;
}
