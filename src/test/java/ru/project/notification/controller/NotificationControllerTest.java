package ru.project.notification.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.project.notification.dto.SendNotificationRequestDTO;
import ru.project.notification.model.NotificationEntity;
import ru.project.notification.service.NotificationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificationControllerTest {

    private final NotificationService notificationService = mock(NotificationService.class);
    private final NotificationController notificationController = new NotificationController(notificationService);

    @Test
    public void testReceiveAndSave() {
        SendNotificationRequestDTO sendNotificationRequestDTO = new SendNotificationRequestDTO(/* заполните данными */);
        NotificationService notificationService = mock(NotificationService.class);
        when(notificationService.saveNotifications(sendNotificationRequestDTO)).thenReturn(true);

        NotificationController notificationController = new NotificationController(notificationService);
        ResponseEntity<String> response = notificationController.receiveAndSave(sendNotificationRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Уведомления успешно сохранены", response.getBody());
    }

    @Test
    public void testGetNotifications() {
        String RqUID = "123";
        Long oldest = 1L;
        Integer pageSize = 10;
        Long employeeNumber = 12345L;

        NotificationService notificationService = mock(NotificationService.class);
        List<NotificationEntity> expectedNotifications = new ArrayList<>(); // добавьте ожидаемые уведомления
        when(notificationService.getNotifications(RqUID, oldest, pageSize, employeeNumber)).thenReturn(expectedNotifications);

        NotificationController notificationController = new NotificationController(notificationService);
        List<NotificationEntity> notifications = notificationController.getNotifications(RqUID, oldest, pageSize, employeeNumber);

        assertEquals(expectedNotifications, notifications);
    }

    @Test
    public void testAllNotificationsAsRead() {
        String RqUID = "123";
        Long employeeNumber = 12345L;

        NotificationService notificationService = mock(NotificationService.class);

        NotificationController notificationController = new NotificationController(notificationService);
        ResponseEntity<String> response = notificationController.allNotificationsAsRead(RqUID, employeeNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("У Вас нет непрочитанных уведомлений", response.getBody());
    }

    @Test
    public void testNotificationsAsReadById() {
        String RqUID = "123";
        Long id = 1L;
        Long employeeNumber = 12345L;

        NotificationService notificationService = mock(NotificationService.class);

        NotificationController notificationController = new NotificationController(notificationService);
        ResponseEntity<String> response = notificationController.notificationsAsReadById(RqUID, id, employeeNumber);

        assertEquals("Уведомлений " + id + " прочитано", response.getBody());
    }


}

