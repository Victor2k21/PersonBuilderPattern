package ru.netology;

import org.junit.jupiter.api.function.Executable;

import java.util.OptionalInt;

public class PersonBuilder {

    protected String name;
    protected String surname;
    protected int age;
    protected String address;

    public PersonBuilder(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.age = -1;
        this.address = null;
    }

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) throws IllegalArgumentException {
        if (age < 0 || age > 125) {
            throw new IllegalArgumentException("Wrong age argument");
        } else {
            this.age = age;
        }
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder("name", this.surname);
        child.setAddress(this.address);
        return child;
    }

    public Person build() throws IllegalStateException {
        if (this != null && this.name != null && this.surname != null) {
            Person human = new Person(this.name, this.surname);
            human.setAddress(this.address);

            if (this.age < 0) return human;
            else human.setAge(this.age);

            return human;
        } else {
            throw new IllegalStateException("Missing required fields");
        }
    }
}
