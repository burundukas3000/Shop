package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String roleName;

    // one roleName can have many users
    // fetchType - default lazy. Getting related users by calling getter()
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH }, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String role) {
        this.roleName = role;
    }

    public Role(String role, Set<User> users) {
        this.roleName = role;
        this.users = users;
    }

    // methods to add/delete applicant
    // returns true if success
    public boolean addUser(User user) {
        boolean added = users.add(user);
        boolean added2 = user.getRoles().add(this);

        return added && added2;
    }

    public boolean deleteUser(User user) {
        boolean deleted = users.remove(user);
        boolean deleted2 = user.getRoles().remove(this);

        return deleted && deleted2;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", roleName=" + roleName + "]";
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role) {
        this.roleName = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}