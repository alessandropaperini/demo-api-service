package it.laterale.cloud.dtos;

import java.util.HashSet;
import java.util.Set;

public class TeamDto {

    private String name;
    private Set<Long> users = new HashSet<>(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getUsers() {
        return users;
    }

    public void setUsers(Set<Long> users) {
        this.users = users;
    }
}
