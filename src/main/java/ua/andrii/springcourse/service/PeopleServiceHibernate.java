package ua.andrii.springcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.andrii.springcourse.dao.PeopleDao;
import ua.andrii.springcourse.model.Person;
import java.util.List;

@Component
@Primary
public class PeopleServiceHibernate implements PeopleService {
    private final PeopleDao peopleDao;

    @Autowired
    public PeopleServiceHibernate(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    public List<Person> getPeople() {
        return peopleDao.getPeople();
    }

    @Override
    public Person getPersonById(int id) {
        return peopleDao.getPersonById(id)
                .orElseThrow(()->new RuntimeException("Can't get person with id " + id + " from DB"));
    }

    @Override
    public void creatPerson(Person person) {
        peopleDao.createPerson(person);
    }

    @Override
    public void updatePerson(int id, Person person) {
        peopleDao.updatePerson(id, person);
    }
                                    // It is not need to do this class,
                                    // because  validation works with PersonValidatorJDBCTemplate class
    @Override
    public void deletePerson(int id) {
        peopleDao.deletePerson(id);
    }
}
