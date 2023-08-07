package ua.andrii.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.andrii.springcourse.model.Person;
import ua.andrii.springcourse.service.PeopleService;
import ua.andrii.springcourse.validator.PersonValidator;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PeopleService peopleService;
    private ua.andrii.springcourse.validator.PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String getPeople(Model model) {
        model.addAttribute("people", peopleService.getPeople());
        return "people/getPeople_page";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id,
                            Model model) {
        model.addAttribute("person", peopleService.getPersonById(id));
        return "people/getPersonPage";
    }

    @GetMapping("/new")
    public String createNewPerson(@ModelAttribute("person") Person person) {
        return "people/createNewPerson_page";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/createNewPerson_page";
        }
        peopleService.creatPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model) {
        model.addAttribute("person", peopleService.getPersonById(id));
        return "people/editPerson_page";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id,
                               @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/editPerson_page";
        }
        peopleService.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.deletePerson(id);
        return "redirect:/people";
    }

}
