package com.example.bfhl;

import com.example.bfhl.dto.BfhlRequestDTO;
import com.example.bfhl.dto.BfhlResponseDTO;
import com.example.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    @Test
    void processData_standardInput() {
        BfhlRequestDTO request = new BfhlRequestDTO(
                Arrays.asList("a", "1", "334", "4", "R", "$")
        );

        BfhlResponseDTO response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("ansh_khatri_26052026", response.getUserId());
        assertEquals("anshkhatri231038@acropolis.in", response.getEmail());
        assertEquals("0827CS231042", response.getRollNumber());

        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    void processData_emptyData() {
        BfhlRequestDTO request = new BfhlRequestDTO(Collections.emptyList());
        BfhlResponseDTO response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void processData_nullData() {
        BfhlRequestDTO request = new BfhlRequestDTO(null);
        BfhlResponseDTO response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals("0", response.getSum());
    }

    @Test
    void processData_onlyNumbers() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("2", "3", "100"));
        BfhlResponseDTO response = service.processData(request);

        assertEquals(List.of("3"), response.getOddNumbers());
        assertEquals(List.of("2", "100"), response.getEvenNumbers());
        assertEquals("105", response.getSum());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
    }

    @Test
    void processData_onlyAlphabets() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("a", "B", "xyz"));
        BfhlResponseDTO response = service.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "B", "XYZ"), response.getAlphabets());
        assertEquals("0", response.getSum());
    }

    @Test
    void processData_concatStringAlternatingCaps() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponseDTO response = service.processData(request);
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    void processData_alphabetsUppercased() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("hello", "world"));
        BfhlResponseDTO response = service.processData(request);
        assertTrue(response.getAlphabets().stream().allMatch(s -> s.equals(s.toUpperCase())));
    }

    @Test
    void processData_sumAsString() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("10", "20", "30"));
        BfhlResponseDTO response = service.processData(request);
        assertEquals("60", response.getSum());
        assertInstanceOf(String.class, response.getSum());
    }

    @Test
    void processData_specialCharacters() {
        BfhlRequestDTO request = new BfhlRequestDTO(Arrays.asList("$", "@", "!"));
        BfhlResponseDTO response = service.processData(request);
        assertTrue(response.getSpecialCharacters().containsAll(List.of("$", "@", "!")));
    }
}
