package ru.project.notification.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NotificationsDTO {

    private List<NotificationDTO> notifications = new ArrayList<>();
    private Boolean hasNext;
    private Boolean hasUnread;
}
