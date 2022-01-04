package com.project.backend.models;

import java.math.BigDecimal;
import java.util.Set;

public class UserActivity {

    private User user;
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal moneySpent;
    private Long freq;
    private String role;

    public UserActivity(User user, BigDecimal moneySpent, Long freq) {
        this.user = user;
        this.moneySpent = moneySpent;
        this.freq = freq;
        this.resolveUserProps();
    }

    private void resolveUserProps() {
        this.id = this.user.getId();
        this.userName = this.user.getUserName();
        this.firstName = this.user.getFirstName();
        this.lastName = this.user.getLastName();
        this.email = this.user.getEmail();
        this.role = this.user.getRoles().iterator().next().getRoleName();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public BigDecimal getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }

    public Long getFreq() {
        return freq;
    }

    public void setFreq(Long freq) {
        this.freq = freq;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}
