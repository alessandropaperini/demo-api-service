package it.laterale.cloud.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Application user.
 */
@Entity
@Data
@Table(name = "application_user")
@NamedQuery(name = "user.findByEmail", query = "from ApplicationUser u where u.email = :email")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

}
