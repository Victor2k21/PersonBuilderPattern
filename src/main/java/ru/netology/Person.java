package ru.netology;

import java.util.OptionalInt;

import static java.util.OptionalInt.*;

public class Person {

    protected final String name;
    protected final String surname;
    protected OptionalInt age;
    protected String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.age = empty();
        this.address = null;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = of(age);
        this.address = null;
    }

    public boolean hasAge() {
        return this.age.isPresent();
    }

    public boolean hasAddress() {
        return (this.address != null);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age.getAsInt();
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = OptionalInt.of(age);
    }

    public void happyBirthday() {
        this.age.ifPresent(age -> {
            this.age = of(age + 1);
        });
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[" + this.name + " " + this.surname);
        if (this.age.isPresent()) s.append(", ").append(this.age.getAsInt());
        if (this.address != null) s.append(", ").append(this.address);
        s.append("]");
        return s.toString();
    }

    @Override
    public int hashCode() {
        int hc = this.name.hashCode();
        return (31 * hc + this.surname.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person prsn = (Person) o;
        if (this.hashCode() != o.hashCode()) return false;
        if (this.name.equals(prsn.name) &&
                this.surname.equals(prsn.surname) &&
                this.age.equals(prsn.age) &&
                this.address.equals(prsn.address)) return true;
        return false;
    }
}
