package com.project.demo.logic;

import com.project.demo.entity.CalendarEvent;
import com.project.demo.logic.exceptions.CalendarEventServiceException;
import com.project.demo.repository.CalendarEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService implements IService<CalendarEvent, Integer>{

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Override
    @Transactional
    public CalendarEvent save(CalendarEvent calendarEventRequest) {
        try{
            CalendarEvent calendarEvent = new CalendarEvent();

            calendarEvent.setTitle(calendarEventRequest.getTitle());
            calendarEvent.setDescription(calendarEventRequest.getDescription());
            calendarEvent.setEventDate(calendarEventRequest.getEventDate());
            calendarEvent.setEventType(calendarEventRequest.getEventType());
            calendarEvent.setOperational(true);
            calendarEvent.setCreation_responsible(calendarEventRequest.getCreation_responsible());
            calendarEvent.setCreation_datetime(calendarEventRequest.getCreation_datetime());


            return calendarEventRepository.save(calendarEvent);
        }  catch (IllegalArgumentException e) {
            throw new CalendarEventServiceException(
                    "Failed to save calendar event: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The calendar event provided is invalid. Please provide a valid event.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new CalendarEventServiceException(
                    "Failed to save calendar event: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The calendar event entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new CalendarEventServiceException(
                    "Failed to save calendar event: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while saving the calendar event. Please try again later.",
                    e
            );
        }

    }

    @Override
    public List<CalendarEvent> findAll() {
        try {
            return calendarEventRepository.findCalendarEventOperational();
        } catch (Exception e) {
            throw new CalendarEventServiceException(
                    "Failed to retrieve all CalendarEvents.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while retrieving CalendarEvents. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Optional<CalendarEvent> findById(Integer integer) {
        return Optional.empty();
    }

    public CalendarEvent findByIdCalendarEvent(Integer integer) {
        try {
            return calendarEventRepository.findById(integer).orElse(null);
        } catch (Exception e) {
            throw new CalendarEventServiceException(
                    "Failed to find CalendarEvent by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while finding the CalendarEvent. Please try again later.",
                    e
            );
        }
    }

    @Override
    public CalendarEvent update(CalendarEvent entity) {
        try {

            return calendarEventRepository.findById(entity.getEventId())
                    .map(calendarEvent -> {

                        calendarEvent.setTitle(entity.getTitle());
                        calendarEvent.setDescription(entity.getDescription());
                        calendarEvent.setEventDate(entity.getEventDate());
                        calendarEvent.setEventType(entity.getEventType());
                        calendarEvent.setUpdate_responsible(entity.getUpdate_responsible());
                        calendarEvent.setLast_update_datetime(entity.getLast_update_datetime());

                        calendarEventRepository.save(calendarEvent);
                        return calendarEvent;
                    })
                    .orElseThrow(() -> new CalendarEventServiceException(
                            "Failed to update CalendarEvent: event not found.",
                            HttpStatus.NOT_FOUND,
                            "EVENT_NOT_FOUND",
                            "The CalendarEvent with the specified ID was not found. Please provide a valid ID.",
                            null
                    ));
        } catch (IllegalArgumentException e) {
            throw new CalendarEventServiceException(
                    "Failed to update CalendarEvent: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The CalendarEvent entity provided is invalid. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new CalendarEventServiceException(
                    "Failed to update CalendarEvent: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The CalendarEvent entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new CalendarEventServiceException(
                    "Failed to update CalendarEvent: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while updating the CalendarEvent. Please try again later.",
                    e
            );
        }
    }

    @Override
    public void deleteById(Integer integer) {
        try {
            calendarEventRepository.findById(integer)
                    .map(existingCalendarEvent -> {
                        existingCalendarEvent.setOperational(false);
                        calendarEventRepository.save(existingCalendarEvent);
                        return true ;
                    });
        } catch (Exception e) {
            throw new CalendarEventServiceException(
                    "Failed to delete CalendarEvent by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while deleting the CalendarEvent. Please try again later.",
                    e
            );
        }
    }
}