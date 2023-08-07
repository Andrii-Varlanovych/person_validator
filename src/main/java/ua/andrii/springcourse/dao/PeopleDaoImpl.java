package ua.andrii.springcourse.dao;

import org.springframework.stereotype.Component;
import ua.andrii.springcourse.db.PeopleDB;
import ua.andrii.springcourse.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleDaoImpl implements PeopleDao {
    @Override
    public List<Person> getPeople() {
        return PeopleDB.personList;
    }

    @Override
    public Optional<Person> getPersonById(int id) {
        return PeopleDB.personList.stream().filter(person -> person.getId() == id).findFirst();
    }

    @Override
    public void createPerson(Person person) {
        PeopleDB.personList.add(person);
    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        System.out.println("Person was deleted in peopleServiceImpl");
    }

    @Override
    public void deletePerson(int id) {
        PeopleDB.personList.removeIf(person -> person.getId() == id);
    }
}
