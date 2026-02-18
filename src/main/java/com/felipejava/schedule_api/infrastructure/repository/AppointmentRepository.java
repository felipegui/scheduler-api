package com.felipejava.schedule_api.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipejava.schedule_api.infrastructure.entity.Appointment;

import jakarta.transaction.Transactional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByServiceAndAppointmentDateTimeBetween(String servive, LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime);

    @Transactional
    //void deleteAppointmentByDateTimeAndCustomer(LocalDateTime appointmentDateTime, String customer);
    void deleteByAppointmentDateTimeAndCustomer(LocalDateTime appointmentDateTime, String customer);

    List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Appointment findByAppointmentDateTimeAndCustomer(LocalDateTime appointmentDateTime, String customer);

}
