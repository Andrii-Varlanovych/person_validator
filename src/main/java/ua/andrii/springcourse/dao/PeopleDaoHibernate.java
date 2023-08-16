package ua.andrii.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.andrii.springcourse.model.Person;
import java.util.List;
import java.util.Optional;


@Component
@Primary
public class PeopleDaoHibernate implements PeopleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PeopleDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> getPeople() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Person> getPersonById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return Optional.of(person);
    }

    @Transactional
    @Override
    public void createPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    @Override
    public void updatePerson(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setAddress(updatedPerson.getAddress());
    }

    @Transactional
    @Override
    public void deletePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);

    }

    /*@Transactional(readOnly = true)
    public Optional<Person> getPersonByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Person personFromDb = session.createQuery("SELECT p from Person WHERE p.email = :personEmail", Person.class)
                .setParameter("personId", email).getSingleResult();
        return Optional.of(personFromDb);
    }*/
}
