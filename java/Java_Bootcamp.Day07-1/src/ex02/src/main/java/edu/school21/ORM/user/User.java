package edu.school21.ORM.user;


import lombok.*;


import javax.persistence.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "simple_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", length = 10)
    private String firstName;
    @Column(name = "last_name", length = 10)
    private String lastName;
    @Column(name = "age")
    private Integer age;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}