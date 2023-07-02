package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Person {
    public static ArrayList<Person> persons = new ArrayList();

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("id")
    private String id;

    public Person(String firstName, String lastName, String id) throws Exception{
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.validate(firstName, lastName, id);
        persons.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private boolean validate (String name, String lastName, String id) throws Exception{
        if (!this.validateId(id)) {
            throw new Exception("chybné rodné číslo");
        } else if (!this.validateName(name)) {
            throw new Exception("chybné jméno");
        } else if (!this.validateName(lastName)) {
            throw new Exception("chybné příjmení");
        }
        return true;
    }

    private boolean validateId(String id) {
        boolean hasCorrectLenth = id.length() == 10;
        int montInt = Integer.parseInt(id.substring(2, 3));
        boolean isCorrect = (montInt > 0 && montInt < 13) || (montInt > 50 && montInt < 63);
        if (!hasCorrectLenth || Long.parseLong(id)%11 != 0 || !isCorrect) {
            return false;
        }
        return true;
    }

    private boolean validateName(String name) {
        if(Character.isUpperCase(name.charAt(0))) {
            return true;
        }
        return false;
    }
}
