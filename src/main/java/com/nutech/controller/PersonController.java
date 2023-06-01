package com.nutech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.dto.PersonDTO;
import com.nutech.dto.ResponseDTO;
import com.nutech.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    // api untuk add person
    @PostMapping("/add")
    public ResponseEntity<?> addPerson(@RequestBody PersonDTO request) {
        try {
            ResponseDTO response = personService.addPerson(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add person", HttpStatus.BAD_REQUEST);
        }

    }

    // api untuk get person yang sudah terkirim dari /add
    @GetMapping("/getAll")
    public ResponseEntity<List<PersonDTO>> getListPerson() {
        try {
            List<PersonDTO> list = personService.getListPerson();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<PersonDTO>>(HttpStatus.BAD_REQUEST);
        }
    }

    // api untuk membaca file profile.txt yang ada di folder resources
    @GetMapping("/readFile")
    public ResponseEntity<String> readFile() {
        try {
            String fileContent = personService.readFile();
            return new ResponseEntity<>(fileContent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to read file", HttpStatus.BAD_REQUEST);
        }
    }
}
