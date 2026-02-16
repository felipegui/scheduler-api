package com.felipejava.schedule_api.services;

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
}
