package br.com.erudio.integrationtest.controller.json;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import br.com.erudio.integrationtest.vo.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.erudio.configs.TestConfigs;
import br.com.erudio.integrationtest.testcontainers.AbstractIntegrationTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest{


    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonVO person;

    @BeforeAll
    public static void setup(){

        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonVO();
    }

    private void mockPerson(){
        person.setFirstName("Shinji");
        person.setLastName("Ikari");
        person.setGender("Male");
        person.setAddress("Neo Tokyo 3 - Japan");
    }

    @Test
    @Order(1)
    public void testCreate() throws IOException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfigs.HEADER_PARAM_ORIGIN,"https://erudio.com.br")
                .setBasePath("/api/person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .build();
        var content =
                given().spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                            .body(person)
                            .post()
                        .then()
                            .statusCode(200)
                        .extract()
                            .body()
                                .asString();
        PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getAddress());

        assertEquals("Shinji", createdPerson.getFirstName());
        assertEquals("Ikari", createdPerson.getLastName());
        assertEquals("Neo Tokyo 3 - Japan", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
    }

}
