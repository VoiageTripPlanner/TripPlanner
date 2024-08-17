package com.project.demo.repository;

import com.project.demo.entity.CalendarEvent;
import com.project.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer> {

    @Query("SELECT u FROM CalendarEvent u WHERE u.operational=true")
    List<CalendarEvent> findCalendarEventOperational();

    @Query("SELECT u FROM CalendarEvent u WHERE u.operational=true AND  u.creation_responsible= :userId")
    List<CalendarEvent> findCalendarEventByUser(@Param("userId") Integer userId);
}
