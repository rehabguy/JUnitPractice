package com.rk.controller;

import com.rk.model.Person;
import com.rk.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/save")
    public void create(@RequestBody Person person){
        personRepository.save(person);
    }

    @GetMapping("/{id}")
    Person getById(@PathVariable int id){
        Person p=personRepository.findById(id).get();
        return p;
    }


}
