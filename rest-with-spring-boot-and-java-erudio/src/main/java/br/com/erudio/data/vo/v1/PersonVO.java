package br.com.erudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"identification", "first_name","last_name", "address","gender"})
public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("identification")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    private String address;
    
    private String gender;

    public PersonVO(){}

    public PersonVO(Long id, String firstName, String address, String lastName, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = address;
        this.address = address;

        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonVO person)) return false;
        return getId().equals(person.getId()) && getFirstName().equals(person.getFirstName()) && getAddress().equals(person.getAddress()) && getLastName().equals(person.getLastName()) && getGender().equals(person.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(),  getLastName() , getAddress() , getGender());
    }
}
