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

    //хотим чтобы формат был "Страна, Город, индекс (6 цифр) - Russia, Leningrad, 019370
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
            message = "Address format should be: Country, City, Postal Code(6 digit)")
    private String address;

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
