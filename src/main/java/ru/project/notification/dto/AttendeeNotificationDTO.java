package ru.project.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendeeNotificationDTO {
    private String fio;
    private Long employeeNumber;

}
