package com.felipejava.schedule_api.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.felipejava.schedule_api.infrastructure.entity.Appointment;
import com.felipejava.schedule_api.services.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.accepted().body(appointmentService.saveAppointment(appointment));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAppointment(@RequestParam String customer, @RequestParam LocalDateTime appointmentDateTime) {
        appointmentService.deleteAppointment(appointmentDateTime, customer);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Appointment>> appointmentOfTheDay(@RequestParam LocalDate date) {
        return ResponseEntity.ok().body(appointmentService.appointmentOfTheDay(date));
    }
    @PutMapping
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment, @RequestParam String customer, @RequestParam LocalDateTime appointmentDateTime) {
        return ResponseEntity.accepted().body(appointmentService.updateAppointment(appointment, customer, appointmentDateTime));
    }
}
