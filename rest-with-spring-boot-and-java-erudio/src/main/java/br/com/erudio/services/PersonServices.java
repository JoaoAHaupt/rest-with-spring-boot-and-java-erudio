package br.com.erudio.services;

import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger  = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person){
        logger.info("Creating one person");

        return person;
    }

    public Person update(Person person){
        logger.info("Updating one person");

        return person;
    }

    public void delete(String id){
        logger.info("Deleting one person");

    }



    public Person findByID(String id){

        logger.info("Finding one person");
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("João");
        person.setLastName("Oliveira");
        person.setAdress("São Paulo - São Paulo - Brazil");
        person.setGender("Male");


        return person;
    }

    public List<Person> findAll(){

        logger.info("Finding all people");

        List<Person> persons = new ArrayList<Person>();
        for(int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Oliveira " + i);
        person.setAdress("Some address - Brazil");
        if(i % 2 == 0){
            person.setGender("Male");
        }
        else{
            person.setGender("Female");
        }
        return person;
    }

}
