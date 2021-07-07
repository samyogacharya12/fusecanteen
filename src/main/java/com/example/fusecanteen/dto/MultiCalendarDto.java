package com.example.fusecanteen.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public class MultiCalendarDto implements Serializable {


    private Long id;


    private ZonedDateTime createdDate;


    private ZonedDateTime updatedDate;


    private boolean deleted;

    private boolean status;

    private LocalDate todayDate;


    private String days;


    private String month;




    private String createdBy;




    private int totalDays;



    private String lastModifiedBy;


    private String bsDate;


    private LocalDate adDate;

    private List<MultiCalendarMonth> multiCalendarMonthList;



    public LocalDate getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }



    public List<MultiCalendarMonth> getMultiCalendarMonthList() {
        return multiCalendarMonthList;
    }

    public void setMultiCalendarMonthList(List<MultiCalendarMonth> multiCalendarMonthList) {
        this.multiCalendarMonthList = multiCalendarMonthList;
    }

    public String getBsDate() {
        return bsDate;
    }


    public void setBsDate(String bsDate) {
        this.bsDate = bsDate;
    }

    public LocalDate getAdDate() {
        return adDate;
    }

    public void setAdDate(LocalDate adDate) {
        this.adDate = adDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }



    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }



    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
