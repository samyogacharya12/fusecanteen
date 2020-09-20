package com.example.fusecanteen.entity;

import com.example.fusecanteen.entity.abstractclasses.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "item")
public class Item extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "code", unique = true)
    private double code;

    @Column(name = "magnifacture_date", nullable = false)
    private LocalDateTime magnifactureDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;


    @OneToMany(mappedBy = "item")
    private List<OrderItem> order;


    public List<OrderItem> getOrder() {
        return order;
    }

    public void setOrder(List<OrderItem> order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        this.code = code;
    }

    public LocalDateTime getMagnifactureDate() {
        return magnifactureDate;
    }

    public void setMagnifactureDate(LocalDateTime magnifactureDate) {
        this.magnifactureDate = magnifactureDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
