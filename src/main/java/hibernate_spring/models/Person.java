package hibernate_spring.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;


@Entity(name = "Person")
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    @Id
    @Column(name = "id")
    private long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max =30, message = "Should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be more than 0")
    @Max(value = 130, message = "Person can't have age more than 120 years")
    @Column(name = "age")
    private int age;

//    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Not valid email format")
//    private String email;
//
//    //хотим чтобы формат был "Страна, Город, индекс (6 цифр) - Russia, Leningrad, 019370
//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
//            message = "Address format should be: Country, City, Postal Code(6 digit)")
//    private String address;
}
