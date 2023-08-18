package ua.andrii.springcourse.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.andrii.springcourse.dao.PeopleDaoJDBC;
import ua.andrii.springcourse.model.Person;

import java.util.Optional;

@Component
@Primary
public class PersonValidatorJDBC implements Validator {
    private PeopleDaoJDBC peopleDaoJDBC;

    @Autowired
    public PersonValidatorJDBC(PeopleDaoJDBC peopleDaoJDBC) {
        this.peopleDaoJDBC = peopleDaoJDBC;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> personByEmail = peopleDaoJDBC.getPersonByEmail(person.getEmail());
        if (personByEmail.isPresent() && personByEmail.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "Person with this email is already exists in DB");
        }
    }
}
