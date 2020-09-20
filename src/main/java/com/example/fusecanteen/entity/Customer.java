package com.example.fusecanteen.entity;

import com.example.fusecanteen.entity.abstractclasses.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "permanent_address", nullable = false)
    private String permanentAddress;

    @Column(name = "temporary_address", nullable = false)
    private String temporaryAddress;

    @OneToMany(mappedBy = "customer")
    private List<OrderItem> order;

    @OneToMany(mappedBy = "customers")
    private List<ItemRequest> itemRequests;


    public List<ItemRequest> getItemRequests() {
        return itemRequests;
    }

    public void setItemRequests(List<ItemRequest> itemRequests) {
        this.itemRequests = itemRequests;
    }

    public List<OrderItem> getOrder() {
        return order;
    }

    public void setOrder(List<OrderItem> order) {
        this.order = order;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }
}
