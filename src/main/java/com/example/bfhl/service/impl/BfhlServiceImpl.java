package com.example.bfhl.service.impl;

import com.example.bfhl.dto.BfhlRequestDTO;
import com.example.bfhl.dto.BfhlResponseDTO;
import com.example.bfhl.service.BfhlService;
import com.example.bfhl.util.DataProcessingUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "ansh_khatri_26052026";
    private static final String EMAIL = "anshkhatri231038@acropolis.in";
    private static final String ROLL_NUMBER = "0827CS231042";

    @Override
    public BfhlResponseDTO processData(BfhlRequestDTO requestDTO) {
        List<String> data = requestDTO.getData();

        if (data == null) {
            data = Collections.emptyList();
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sumValue = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            if (DataProcessingUtil.isNumber(item)) {
                long num = Long.parseLong(item);
                sumValue += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (DataProcessingUtil.isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());
            } else if (DataProcessingUtil.isSpecialCharacter(item)) {
                specialCharacters.add(item);
            } else {
                // Mixed strings: classify each character
                for (char c : item.toCharArray()) {
                    String ch = String.valueOf(c);
                    if (Character.isDigit(c)) {
                        // handled as part of full token check; skip individual chars
                    } else if (Character.isLetter(c)) {
                        // individual char alphabets are not added here;
                        // the whole token is already classified above
                    } else {
                        specialCharacters.add(ch);
                    }
                }
            }
        }

        String concatString = DataProcessingUtil.buildConcatString(data);

        return BfhlResponseDTO.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sumValue))
                .concatString(concatString)
                .build();
    }
}
