package com.example.reposetory;

import com.example.model.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class PeopleRepo {
    @Autowired
    SessionFactory factory ;

    public List<People> getAll(){
        Session session = this.factory.getCurrentSession();
//        List peoples = session.createQuery("from People").list();
        Query from_people = session.createQuery("from People");
        List<People> resultList = from_people.list();
        return resultList;
    }

    public People save(People people){
        factory.openSession().save(people);
        return people;
    }
    public People getById(Long id){
        return (People) factory.getCurrentSession()
                .createQuery("from People p where p.id = :id")
                .setParameter("id",id).getSingleResult();
    }
    public String deleteById(Long id){
//        factory.getCurrentSession().delete(getById(id));
        People people = new People();
        people.setId(id);
        factory.getCurrentSession().delete(people);
        return "success delete people id=" + id;
    }
    public People update(People people, Long id){
        people.setId(id);
        factory.getCurrentSession().update(people);
        return getByName(people.getName());
    }
    public People getByName(String name){
        return (People) factory.getCurrentSession()
                .createQuery("from People p where p.name = :name")
                .setParameter("name",name).getSingleResult();
    }
}