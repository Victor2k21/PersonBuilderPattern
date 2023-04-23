package ru.netology;

public class Main {
    public static void main(String[] args) {

        Person mom = new PersonBuilder("Ann", "Smirnoff").build();
        System.out.println("Mom " + mom.toString());

        mom.setAge(28);
        mom.setAddress("Moscow");
        System.out.println("Mom " + mom.toString());

        PersonBuilder dad = new PersonBuilder("Nick ", mom.getSurname())
                .setAge(32)
                .setAddress(mom.getAddress());

        PersonBuilder boy = dad.newChildBuilder();
        Person son = boy.setName("Peet")
                .setAge(10)
                .build();
        Person daddy = dad.build();
        System.out.println("Dad " + daddy.toString());
        System.out.println("Son " + son.toString());

        son.happyBirthday();
        System.out.println("Son, happy birsday! " + son.toString());

        // dad.setAge(-2).build();
        Person s = new PersonBuilder("Ann", null).build();
        System.out.println(s.toString());
    }
}