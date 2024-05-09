package ru.project.notification.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class NotificationDTO {

    private Long id;
    private String productName;
    private String subject;
    private String content;
    private String image;
    private OffsetDateTime date;
    private Boolean _new;
    private Boolean read;
    private String link;
    private String type;
}
