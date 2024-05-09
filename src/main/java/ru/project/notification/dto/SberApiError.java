package ru.project.notification.dto;

import lombok.Data;

@Data
public class SberApiError {

    private String httpMessage;
    private String moreInformation;
}
