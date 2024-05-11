package ru.project.notification.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetNotificationsResponseDTO {

    private Boolean success; //Статус выполнения запроса: true/false
    private NotificationsDTO body; //NotificationsDTO
    private List<MessageDTO> messages;
    private List<AlertDTO> alerts;
    private ErrorDTO error;
}
