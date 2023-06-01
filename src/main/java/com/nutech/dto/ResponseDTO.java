package com.nutech.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private String error;
    private String message;
    private Person value;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {
        private String name;
        private Integer nik;
        private LocalDate tanggalLahir;
    }
}
