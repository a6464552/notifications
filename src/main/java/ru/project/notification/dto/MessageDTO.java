package ru.project.notification.dto;

import lombok.Data;

@Data
public class MessageDTO {

    private String title;
    private String text;
    private String type;
}
