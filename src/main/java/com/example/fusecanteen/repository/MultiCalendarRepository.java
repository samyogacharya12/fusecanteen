package com.example.fusecanteen.repository;

import com.example.fusecanteen.entity.MultiCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiCalendarRepository extends JpaRepository<MultiCalendar, Long> {
}
