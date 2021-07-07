package com.example.fusecanteen.repository;

import com.example.fusecanteen.entity.MultiCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MultiCalendarRepository extends JpaRepository<MultiCalendar, Long> {


    MultiCalendar findByBsDate(String bsDate);


    MultiCalendar findByAdDate(LocalDate adDate);





    @Query("select rs from MultiCalendar rs where rs.bsDate like :bsDate% order by rs.adDate desc ")
    List<MultiCalendar> findAllByBsDateOrderByAdDateDesc
            (@Param(value = "bsDate") String bsDate);


}
