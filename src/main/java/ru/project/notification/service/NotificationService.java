package ru.project.notification.service;

import ru.project.notification.dto.SendNotificationRequestDTO;
import ru.project.notification.model.NotificationEntity;

import java.util.List;

public interface NotificationService {
    boolean saveNotifications(SendNotificationRequestDTO sendNotificationRequestDTO);

    List<NotificationEntity> getNotifications(String RqUID, Long oldest, Integer pageSize, Long employeeNumber);

    void countUnreadNotifications(Long employeeNumber);

    void notificationHasRead(Long id);
}
