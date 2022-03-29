package it.laterale.cloud.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    private Long id;
    private String name;
    private Set<User> members = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }
}
