package ru.project.notification.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SendNotificationRequestDTO {

    private String channel;
    private String productId;
    private String subject;
    private List<AttendeeNotificationDTO> to;
    private String type;
    private String content;
    private Integer storageTime;
    private String image;
    private String link;

}
