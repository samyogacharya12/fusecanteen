package com.example.fusecanteen.entity;

import com.example.fusecanteen.entity.abstractclasses.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "item_request")
public class ItemRequest extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "type", nullable = false)
    private String type;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customers;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customers) {
        this.customers = customers;
    }
}
