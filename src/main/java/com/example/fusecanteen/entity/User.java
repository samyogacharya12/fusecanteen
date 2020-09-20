package com.example.fusecanteen.entity;

import com.example.fusecanteen.entity.abstractclasses.AbstractEntity;
import com.example.fusecanteen.entity.security.UserRole;
import com.example.fusecanteen.enumconstant.AuthorityType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String mobile;

    @NotNull
    private AuthorityType userType;


    private long associatedId;

    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<OrderItem> order;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();


    public List<OrderItem> getOrder() {
        return order;
    }

    public void setOrder(List<OrderItem> order) {
        this.order = order;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public AuthorityType getUserType() {
        return userType;
    }

    public void setUserType(AuthorityType userType) {
        this.userType = userType;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(long associatedId) {
        this.associatedId = associatedId;
    }

}
