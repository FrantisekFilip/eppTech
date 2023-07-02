package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleIO {

    private PersonService personService;

    @Autowired
    public ConsoleIO(PersonService personService) {
        this.personService = personService;
    }

    public void menu() throws Exception {
        this.printMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1:
                    this.createPerson();
                    break;
                case 2:
                    this.removePerson();
                    break;
                case 3:
                    this.findPerson();
                    break;
                default:
                    this.wrongChoice();
            }
        } catch (Exception e) {
            this.wrongChoice();
        }
    }

    private void findPerson() throws Exception {
        System.out.println("Zadej rodné číslo osoby k vyhledání");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String id = reader.readLine();
        try {
            Person person = personService.findPersonById(id);
            if (person != null) {
                System.out.println("Nalezená osoba:");
                System.out.println("Jméno: " + person.getFirstName());
                System.out.println("Příjmení: " + person.getLastName());
                System.out.println("Rodné číslo: " + person.getId());
            } else {
                System.out.println("Osoba se zadaným ID nebyla nalezena");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.menu();
    }

    private void removePerson() throws Exception {
        System.out.println("Zadej rodné číslo osoby k odstranění");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String id = reader.readLine();
        try {
            personService.removePerson(id);
            System.out.println("Osoba s ID " + id + " byla odstraněna");
        } catch (Exception e) {
            System.out.println(e);
        }
        this.menu();
    }

    private void createPerson() throws Exception {
        System.out.println("Zadej jméno");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println("Zadej příjmení");
        String lastName = reader.readLine();
        System.out.println("Zadej rodné číslo");
        String id = reader.readLine();
        try {
            personService.createPerson(new Person(name, lastName, id));
            System.out.println("Osoba byla úspěšně vytvořena");
        } catch (Exception e) {
            System.out.println(e);
        }
        this.menu();
    }

    private void printMenu() {
        System.out.println("Zvol možnost");
        System.out.println("Přidat osobu: 1");
        System.out.println("Odebrat osobu: 2");
        System.out.println("Vyhledat osobu: 3");
    }

    private void wrongChoice() throws Exception {
        System.out.println("Chybná volba");
        this.menu();
    }
}
