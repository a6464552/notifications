package ru.project.notification.dto;

import lombok.Data;

@Data
public class ProducerError implements OneOferror {

    private String producerErrorCode;
    private String producerErrorMessage;

}
