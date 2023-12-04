package br.com.erudio.mapper.custom;

import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.model.Person;
import jakarta.persistence.Entity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getFirstName());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());

        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 vo){
        Person person = new Person();

        person.setId(vo.getId());
        person.setAddress(vo.getAddress());
        person.setFirstName(vo.getFirstName());
        person.setLastName(vo.getFirstName());
        person.setGender(vo.getGender());
        // person.setBirthDay(new Date());

        return person;
    }

}
