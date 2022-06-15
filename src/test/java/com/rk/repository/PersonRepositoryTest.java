package com.rk.repository;

import com.rk.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void saveEmployeeTest(){

        Person p = Person.builder()
                .name("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        personRepository.save(p);

        Assertions.assertThat(p.getId()).isGreaterThan(0);
    }

    @Test
    public void getByIdTesting(){
        Person person=personRepository.findById(1).get();
        Assertions.assertThat(person.getId()).isEqualTo(1);
    }

    @Test
    public void getListOfPersonTest(){

        List<Person> employees = personRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    public void updatePersonTest(){

        Person person = personRepository.findById(1).get();

        person.setEmail("ram@gmail.com");

        Person updated =  personRepository.save(person);

        Assertions.assertThat(updated.getEmail()).isEqualTo("ram@gmail.com");

    }

    @Test
    public void deletePersonTest(){

        Person person = personRepository.findById(1).get();

        personRepository.delete(person);

        //employeeRepository.deleteById(1L);

        Person p1 = null;

        Optional<Person> personOptional = personRepository.findByEmail("ram@gmail.com");

        if(personOptional.isPresent()){
            p1 = personOptional.get();
        }

        Assertions.assertThat(p1).isNull();
    }



}
