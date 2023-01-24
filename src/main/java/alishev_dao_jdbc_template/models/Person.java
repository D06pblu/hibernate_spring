package alishev_dao_jdbc_template.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max =30, message = "Should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be more than 0")
    @Max(value = 130, message = "Person can't have age more than 120 years")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Not valid email format")
    private String email;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {
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
}