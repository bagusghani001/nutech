package com.nutech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.dto.PersonDTO;
import com.nutech.dto.ResponseDTO;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @PostMapping("/add")
    public ResponseEntity<String> addPerson(PersonDTO request){

        return ResponseEntity.ok();
    }
}
