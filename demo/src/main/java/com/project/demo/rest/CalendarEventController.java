package com.project.demo.rest;

import org.springframework.web.bind.annotation.RequestParam;

public ResponseEntity<List<CalendarEvent>> retrieveAll(@RequestParam("userId") Integer userId) {

        return ResponseEntity.ok(calendarEventService.findAllEventsByUser(userId));
        }


        import com.project.demo.entity.CalendarEvent;
import com.project.demo.entity.Trip;
import com.project.demo.entity.User;
import com.project.demo.entity.request.UserRequest;
import com.project.demo.logic.CalendarEventService;
import com.project.demo.repository.CalendarEventRepository;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendarEvent")
public class CalendarEventController {


    @Autowired
    private CalendarEventService calendarEventService;

    @GetMapping

    @PostMapping
    public ResponseEntity<CalendarEvent> create(@RequestBody CalendarEvent entity) {

        return ResponseEntity.ok(calendarEventService.save(entity));
    }


    @GetMapping("/{id}")
    public Optional<CalendarEvent> retrieveById(@PathVariable Integer id) {
        if (calendarEventService.findById(id).isPresent()) {
            return calendarEventService.findById(id);
        }
        return Optional.empty();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarEvent> update(@RequestBody CalendarEvent entity) {

        return ResponseEntity.ok(calendarEventService.update(entity));
    }
    @PutMapping("delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        calendarEventService.deleteById(id);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public User authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}