package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private List<Person> persons;

    public PersonService() {
        this.persons = new ArrayList<>();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person createPerson(Person person) throws Exception {
        if (persons.stream().anyMatch(p -> p.getId().equals(person.getId()))) {
            throw new Exception("Osoba s tímto rodným číslem již existuje");
        }
        persons.add(person);
        return person;
    }

    public boolean removePerson(String id) {
        Person personToRemove = persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (personToRemove != null) {
            persons.remove(personToRemove);
            return true;
        } else {
            return false;
        }
    }

    public Person findPersonById(String id) throws Exception {
        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Person getPersonById(String id) throws Exception {
        return findPersonById(id);
    }
}
