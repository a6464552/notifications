package ru.project.notification.dto;

import lombok.Data;

@Data
public class ErrorDTO {
    private String code; //код ошибки
    private String system;
    private String title;
    private String text;
    private String uuid;
}
