package com.felipejava.schedule_api.infrastructure.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejava.schedule_api.infrastructure.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByServiceAndAppointmentDateTimeBetween(String servive, LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime);

}
