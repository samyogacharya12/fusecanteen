package com.example.fusecanteen.entity;


import com.example.fusecanteen.enumconstant.Days;
import com.example.fusecanteen.enumconstant.Months;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "relate_bs_ad")
public class RelateBsAd implements Serializable {


    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "created_by")
    private User createdBy;


    @Column(name = "last_modified_by")
    private User lastModifiedBy;


    @NotNull
    @Column(name = "created_date", nullable = false)
    private ZonedDateTime createdDate;

    @NotNull
    @Column(name = "updated_date", nullable = false)
    private ZonedDateTime updatedDate;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "status", nullable = false)
    private boolean status;


    @NotNull
    @Column(name = "days", nullable = false)
    @Enumerated(EnumType.STRING)
    private Days days;


    @NotNull
    @Column(name = "months", nullable = false)
    @Enumerated(EnumType.STRING)
    private Months months;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    public Months getMonths() {
        return months;
    }

    public void setMonths(Months months) {
        this.months = months;
    }
}
