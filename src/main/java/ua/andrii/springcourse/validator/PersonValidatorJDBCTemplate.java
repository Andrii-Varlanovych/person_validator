package ua.andrii.springcourse.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ua.andrii.springcourse.dao.PeopleDaoJDBCTemplate;
import ua.andrii.springcourse.model.Person;

@Component
@Primary
public class PersonValidatorJDBCTemplate implements PersonValidator {
    private PeopleDaoJDBCTemplate peopleDaoJDBCTemplate;

    @Autowired
    public PersonValidatorJDBCTemplate(PeopleDaoJDBCTemplate peopleDaoJDBCTemplate) {
        this.peopleDaoJDBCTemplate = peopleDaoJDBCTemplate;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (peopleDaoJDBCTemplate.getPersonByEmail(person.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Person with this email is already exists in DB");
        }
    }
}
