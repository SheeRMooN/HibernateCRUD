package com.example.service;

import com.example.model.People;
import com.example.reposetory.PeopleRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@Tag("DAO")
public class PeopleRepoTest {
    People people1;
    People people2 ;
    List<People> peopleList = new ArrayList<>();

    @MockBean
    private SessionFactory sessionFactory;
    @MockBean
    private Session session;

    private PeopleRepo repo;

    @BeforeEach
    public void prepare() throws Exception {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        repo = new PeopleRepo(sessionFactory);
        people1 =new People(1l,0,"name1");
        people2 =new People(2l,0,"name2");
        peopleList.add(people1);
        peopleList.add(people2);
    }

    @AfterEach
    void tearDown() {
        peopleList.clear();
        people1=null;
        people2=null;
    }

    @Test
    void getAllTest() {
        Query q = Mockito.mock(Query.class);
        when(session.createQuery("from People")).thenReturn(q);
        when((q).list()).thenReturn(peopleList);
        List list = repo.getAll();
        assertAll("People",()-> assertNotEquals(list,null),()->assertEquals(list.size(),2));
    }

    @Test
    void getByIdTest(){
        Query query = Mockito.mock(Query.class);
        when(session.createQuery("from People p where p.id = :id")).thenReturn(query);
        when(query.setParameter("id",1L)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(people1);
//        when(session.get(People.class, 1L)).thenReturn(new People(1L,0,"name")); // alternative
        assertEquals(people1, repo.getById(1L));
    }
    @Test
    void getByNameTest(){
        Query query = Mockito.mock(Query.class);
        when(session.createQuery("from People p where p.name = :name")).thenReturn(query);
        when(query.setParameter("name","name1")).thenReturn(query);
        when(query.getSingleResult()).thenReturn(people1);
        assertEquals(people1,repo.getByName("name1"));
    }
    @Test
    void updateTest(){
        repo.update(people1,1L);
        verify(session,times(1)).update(people1);
    }
    @Test
    void deleteTest(){
        repo.deleteById(1L);
        verify(session,times(1)).delete(new People(1L,0,null));
    }
    @Test
    void saveTest(){
        repo.save(people1);
        verify(session,times(1)).save(people1);
    }
}