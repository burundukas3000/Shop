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

    // one roleName can have many users and opposite. Bidirectional mapping
    // fetchType - default lazy. Getting related users by calling getter()
    // cascade = all operations except DELETE are performed on associated table
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JoinTable(name="users_roles",
                joinColumns = { @JoinColumn(name="role_id") },
                inverseJoinColumns = { @JoinColumn(name="user_id")})
    private Set<User> users = new HashSet<User>();

    public Role() {
    }

    public Role(String role) {
        this.roleName = role;
    }

    public Role(String role, Set<User> users) {
        this.roleName = role;
        this.users = users;
    }

    // for @ManyToMany association - methods to add/delete customer
    // returns true if success
    public boolean addUser(User user) {
        boolean added = this.users.add(user);
        boolean added2 = user.getRoles().add(this);

        return added && added2;
    }

    public boolean deleteUser(User user) {
        boolean deleted = this.users.remove(user);
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