package ru.project.notification.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadNotificationResponseDTO {

    private Boolean success; //Статус выполнения запроса: true/false
    private CountDTO body;
    private List<MessageDTO> messages;
    private List<AlertDTO> alerts;
    private ErrorDTO error;

}
