package com.example.service;

import com.example.model.People;
import com.example.reposetory.PeopleRepo;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PeopleService {
    PeopleRepo repo;
    private SessionFactory sessionFactory;

    @Transactional
    public List<People> getAll(){
        return repo.getAll();
    }
    @Transactional
    public People save(People people){
        return repo.save(people);
    }
    @Transactional
    public void delete(Long id){
        repo.deleteById(id);
    }
    @Transactional
    public People update(People people, Long id){
        people.setId(id);
        return repo.save(people);
    }
    @Transactional
    public People getById(Long id){
        return repo.getById(id);
    }
}
