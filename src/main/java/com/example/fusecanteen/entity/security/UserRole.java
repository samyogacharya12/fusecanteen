package com.example.fusecanteen.entity.security;


import com.example.fusecanteen.entity.User;
import com.example.fusecanteen.entity.abstractclasses.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole extends AbstractEntity {


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {

        this.user = user;
        this.role = role;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


}
