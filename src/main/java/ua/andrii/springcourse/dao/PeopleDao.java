package ua.andrii.springcourse.dao;

import ua.andrii.springcourse.model.Person;

import java.util.List;
import java.util.Optional;

public interface PeopleDao {
    List<Person> getPeople();

    Optional<Person> getPersonById(int id);

    void createPerson(Person person);
    void updatePerson(int id, Person updatedPerson);

    void deletePerson(int id);
}
