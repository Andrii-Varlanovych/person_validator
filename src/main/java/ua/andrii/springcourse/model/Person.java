package ua.andrii.springcourse.model;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name could not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;
    @Min(value = 2, message = "Age should be greater 2")
    @Max(value = 120, message = "Age should be less than 120")
    private int age;
    @NotEmpty(message = "Email could not be empty")
    @Email(message = "This is not an email")
    private String email;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Address should be : Country, City, 123456")
    private String address;

    public Person() {
    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
