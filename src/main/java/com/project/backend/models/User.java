package com.project.backend.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "username")
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String email;

    // one user can have many roles
    // fetchType = default -lazy. Setting to eager - getting roles when getting user.
    // cascade = all operations except DELETE are performed on associated table
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH })
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
    }

    public User(String userName, String password, String email, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    // methods to add/delete role
    // returns true if success
    public boolean addRole(Role role) {
        boolean added = roles.add(role);
        boolean added2 = role.getUsers().add(this);

        return added && added2;
    }

    public boolean deleteRole(Role role) {
        boolean deleted = roles.remove(role);
        boolean deleted2 = role.getUsers().remove(this);

        return deleted && deleted2;
    }

    // setters and getters
    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

