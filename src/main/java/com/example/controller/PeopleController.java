package com.example.controller;

import com.example.model.People;
import com.example.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {
    PeopleService service;

    @GetMapping("/all")
    public List<People> getAll(){
        return service.getAll();
    }

    @PostMapping("/create")
    public People create(@RequestBody People people){
        return service.save(people);
    }
    @GetMapping("/get/{id}")
    public People getById(@PathVariable Long id){
        return service.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "success delete =" +id;
    }
    @PutMapping("/put/{id}")
    public People up(@RequestBody People people, @PathVariable Long id){
        return service.update(people, id);
    }
}
