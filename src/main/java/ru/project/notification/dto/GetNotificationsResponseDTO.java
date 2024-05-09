package ru.project.notification.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetNotificationsResponseDTO {

    private Boolean success;
    private NotificationsDTO body;
    private List<MessageDTO> messages;
    private List<AlertDTO> alerts;
    private ErrorDTO error;
}
