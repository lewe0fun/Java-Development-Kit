package seminar4.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /**
         * Создать класс справочник сотрудников, который содержит внутри коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
         * Табельный номер
         * Номер телефона
         * Имя
         * Стаж
         * Добавить метод, который ищет сотрудника по стажу (может быть список)
         * Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
         * Добавить метод, который ищет сотрудника по табельному номеру
         * Добавить метод добавление нового сотрудника в справочник сотрудников
         */
        System.out.println();
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person(1, "228(16)686-20-23", "Zura", 50),
                new Person(2, "9(9704)451-61-38", "Zura", 50),
                new Person(3, "390(19)117-35-58", "Mura", 60),
                new Person(4, "585(7154)181-52-13", "Tura", 20)
        ));
        PhoneBook phoneBook = new PhoneBook(people);
        /**
         * Добавление
         */
        phoneBook.add(new Person(5, "654987", "GHRTE", 45));
        /**
         * Поиск по номеру
         */
        System.out.println("Поиск по номеру");
        System.out.println(phoneBook.findPersonById(5));
        /**
         * Поиск по стажу
         */
        System.out.println("Поиск по стажу");
        System.out.println(new PhoneBook(phoneBook.findPersonByExp(50)));
         /**
        * номер телефона сотрудника по имени
        */
        System.out.println("номер телефона сотрудника по имени ");
        System.out.println(phoneBook. getPhoneByName("Zura"));
    }

}
