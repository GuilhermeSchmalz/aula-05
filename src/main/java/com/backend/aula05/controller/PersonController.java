package com.backend.aula05.controller;

import com.backend.aula05.dto.PersonDto;
import com.backend.aula05.model.Person;
import com.backend.aula05.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personRepository.save(person));
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPeople() {
        List<Person> people = personRepository
                .findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(people);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable UUID id) {
        Optional<Person> personOptional = personRepository
                .findById(id);
        return personOptional.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!")
                : ResponseEntity.status(HttpStatus.OK).body(personOptional.get());
    }

}
