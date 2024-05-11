package ru.project.notification.repository;

import org.junit.jupiter.api.Test;
import ru.project.notification.model.NotificationEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificationDataRepositoryTest {

    @Test
    public void testGetByEmployeeNumber() {
        Long employeeNumber = 12345L;
        Long oldest = 1L;

        NotificationDataRepository notificationDataRepository = mock(NotificationDataRepository.class);
        List<NotificationEntity> expectedNotifications = new ArrayList<>(); // добавьте ожидаемые уведомления
        when(notificationDataRepository.getByEmployeeNumber(employeeNumber, oldest)).thenReturn(expectedNotifications);

        List<NotificationEntity> notifications = notificationDataRepository.getByEmployeeNumber(employeeNumber, oldest);

        assertEquals(expectedNotifications, notifications);
    }

    @Test
    public void testFindByEmployeeNumberAndReaded() {
        Long employeeNumber = 12345L;
        boolean readed = true;

        NotificationDataRepository notificationDataRepository = mock(NotificationDataRepository.class);
        List<NotificationEntity> expectedNotifications = new ArrayList<>(); // добавьте ожидаемые уведомления
        when(notificationDataRepository.findByEmployeeNumberAndReaded(employeeNumber, readed)).thenReturn(expectedNotifications);

        List<NotificationEntity> notifications = notificationDataRepository.findByEmployeeNumberAndReaded(employeeNumber, readed);

        assertEquals(expectedNotifications, notifications);
    }
}
