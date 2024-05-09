package ru.project.notification.dto;

import lombok.Data;

@Data
public class AlertDTO {

    private String title; //заголовок оповещения
    private String text;
    private String type;
    private Object data;
}
