package com.example.demo;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PersonsList {

    private ArrayList<Person> persons;

    public PersonsList() {
        this.persons = new ArrayList<>();
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }
}
