package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long ageBefore18 = persons.stream()
                .filter(person -> person.getAge() > 18)
                .count();
        System.out.println("Количество несовершеннолетних " + ageBefore18);

        List<String> listOfSolders = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println("Фамилии призывников " + listOfSolders);

        List<Person> educatedPerson = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() >= 18 && person.getAge() <= 65
                        : person.getAge() >= 18 && person.getAge() <= 60)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
        System.out.print("Фамилии потенциальных работоспособных людей с высшим образованием: ");
        for (int i = 0; i < educatedPerson.size(); i++) {
            System.out.print(educatedPerson.get(i).getFamily() + " " + educatedPerson.get(i).getName() + "; ");
            System.out.print(" ");
        }
    }
}
