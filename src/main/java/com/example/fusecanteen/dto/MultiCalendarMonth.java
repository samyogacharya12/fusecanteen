package com.example.fusecanteen.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MultiCalendarMonth implements Serializable {


    private Long id;

    @NotNull
    private String month;

    private int totalDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }
}
