package com.nutech.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nutech.dto.PersonDTO;
import com.nutech.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {
    
    //membuat add person

    private List<PersonDTO> listPerson = new ArrayList<>();

    ResponseDTO response = new ResponseDTO();
    
    public ResponseDTO addPerson(PersonDTO request){
        if(isValidData(request)){
            //cek kalau valid akan di tambahkan /di masukan ke file profile.txt
            listPerson.add(request);
            writePerson(request);
            response.setError("false");
            response.setMessage("sukses");
            response.setValue(listPerson.stream().map(PersonDTO::toString).collect(Collectors.toList()));
            return response;
        }else{
            throw new IllegalArgumentException("Invalid Request");
        }
    }

    public ResponseDTO getListPerson(){
        response.setError("false");
        response.setMessage("sukses");
        response.setValue(listPerson.stream().map(PersonDTO::toString).collect(Collectors.toList()));
        return response;
    }

    //function validasi cek data request 
    public boolean isValidData(PersonDTO request){
        return request.getName()!=null && request.getNik()!=null && request.getTanggalLahir()!=null;
    }

    //Function Menulis Person
    public void writePerson(PersonDTO request){
        try (BufferedWriter writer= new BufferedWriter(new FileWriter("../resource/profile.txt"))) {
            String formatDates = formatDate(request.getTanggalLahir());
            String line = String.valueOf(request.getNik()) + ","+request.getName()+","+formatDates;
            writer.write(line);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //function formatiing date 
    public String formatDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(dtf);
    }

    //function untuk membaca file
    public String readFile(){
        try (BufferedReader read = new BufferedReader(new FileReader("../resource/profile.txt"))) {
            return read.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new RuntimeException("File tidak ada/ tidak kebaca");
        }
    }


}
