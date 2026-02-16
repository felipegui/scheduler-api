package com.felipejava.schedule_api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.felipejava.schedule_api.infrastructure.entity.Appointment;
import com.felipejava.schedule_api.infrastructure.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment) {

        LocalDateTime appointmenTime = appointment.getAppointmentDateTime();

        LocalDateTime appointmentEndTime = appointment.getAppointmentDateTime().plusHours(1);

        Appointment existingAppointment = appointmentRepository.findByServiceAndAppointmentDateTimeBetween(appointment.getService(), appointmenTime, appointmentEndTime);

        if (Objects.nonNull(existingAppointment)) {
            throw new RuntimeException("Já existe um agendamento para esse serviço nesse horário.");
        }
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(LocalDateTime appointmentDateTime, String customer) {
        appointmentRepository.deleteAppointmentByDateTimeAndCustomer(appointmentDateTime, customer);
    }

    public Appointment appointmentOfTheDay(LocalDate date) {
        LocalDateTime firstHourOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return appointmentRepository.findByAppointmentDateTimeBetween(firstHourOfDay, endOfDay);
    }

    public Appointment updateAppointment(Appointment appointment, String customer, LocalDateTime appointmentDateTime) {
        Appointment appointmentSchedule = appointmentRepository.findByAppointmentDateTimeAndCustomer(appointmentDateTime, customer);

        if (Objects.isNull(appointmentSchedule)) {
            throw new RuntimeException("Horário não está preenchido!");
        }

        appointment.setId(appointmentSchedule.getId());
        return appointmentRepository.save(appointmentSchedule);
    }
}
