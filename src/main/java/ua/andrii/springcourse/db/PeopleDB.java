package ua.andrii.springcourse.db;

import ua.andrii.springcourse.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PeopleDB {
    public static int PEOPLE_ID;
    public static List<Person> personList;

    static {
        personList = new ArrayList<>();
        personList.add(new Person(++PEOPLE_ID, "Tom", 10, "tom@ukr.net", "Ukraine, Chernivtsy, 58000"));
        personList.add(new Person(++PEOPLE_ID, "Jerry", 3, "jerry@ukr.net", "Ukraine, Chernivtsy, 58001"));
        personList.add(new Person(++PEOPLE_ID, "Butch", 5, "butc@ukr.net", "Ukraine, Chernivtsy, 58002"));
    }
}
