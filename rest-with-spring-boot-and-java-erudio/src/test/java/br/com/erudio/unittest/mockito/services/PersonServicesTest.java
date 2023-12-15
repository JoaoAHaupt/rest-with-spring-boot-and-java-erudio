package br.com.erudio.unittest.mockito.services;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.mapper.Mapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonServices;
import br.com.erudio.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static  org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices personServices;

    @Mock
    PersonRepository repository;

    Mapper mapper;

    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var people = personServices.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personNine = people.get(9);

        assertNotNull(personNine);
        assertNotNull(personNine.getKey());
        assertNotNull(personNine.getLinks());

        assertTrue(personNine.toString().contains("links: [</api/person/v1/9>;rel=\"self\"]"));
        assertEquals("Address Test9", personNine.getAddress());
        assertEquals("First Name Test9", personNine.getFirstName());
        assertEquals("Last Name Test9", personNine.getLastName());
        assertEquals("Female", personNine.getGender());

        var personThirteen = people.get(13);

        assertNotNull(personThirteen);
        assertNotNull(personThirteen.getKey());
        assertNotNull(personThirteen.getLinks());

        assertTrue(personThirteen.toString().contains("links: [</api/person/v1/13>;rel=\"self\"]"));
        assertEquals("Address Test13", personThirteen.getAddress());
        assertEquals("First Name Test13", personThirteen.getFirstName());
        assertEquals("Last Name Test13", personThirteen.getLastName());
        assertEquals("Female", personThirteen.getGender());
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = personServices.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertEquals("Address Test1",result.getAddress());
        assertEquals("Female",result.getGender());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
    }

    @Test
    void testCreate() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        Person persisted = person;
        persisted.setId(1L);

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        Mockito.lenient().when(repository.save(person)).thenReturn(persisted);

        var result = personServices.create(personVO);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()-> {
            personServices.create(null);});

        String expected = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expected));

    }

    @Test
    void testUpdateNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()-> {
            personServices.update(null);});

        String expected = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expected));

    }

    @Test
    void update() {

        Person person = input.mockEntity(1);
        person.setId(1L);

        Person persisted = person;
        persisted.setId(1L);

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        Mockito.lenient().when(repository.save(person)).thenReturn(persisted);

        var result = personServices.update(personVO);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));
        personServices.delete(1L);

    }
}