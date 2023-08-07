package ua.andrii.springcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.andrii.springcourse.dao.PeopleDao;
import ua.andrii.springcourse.db.PeopleDB;
import ua.andrii.springcourse.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleServiceImpl implements PeopleService {
    private PeopleDao peopleDao;

    @Autowired
    public PeopleServiceImpl(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    public List<Person> getPeople() {
        return peopleDao.getPeople();
    }

    @Override
    public Person getPersonById(int id) {
        return peopleDao.getPersonById(id)
                .orElseThrow(()->new RuntimeException("Can't find person with id " + id + " in DB"));
    }

    @Override
    public void creatPerson(Person person) {
        person.setId(++PeopleDB.PEOPLE_ID);
        peopleDao.createPerson(person);

    }

    @Override
    public void updatePerson(int id, Person updatedPerson) {
        Person personToBeUpdated = peopleDao.getPersonById(id)
                .orElseThrow(() -> new RuntimeException("Can't get person with id " + id + " from DB"));
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Override
    public void deletePerson(int id) {
        peopleDao.deletePerson(id);
    }
}
