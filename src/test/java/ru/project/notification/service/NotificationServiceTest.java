package ru.project.notification.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.project.notification.dto.AttendeeNotificationDTO;
import ru.project.notification.dto.SendNotificationRequestDTO;
import ru.project.notification.model.NotificationEntity;
import ru.project.notification.repository.NotificationDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    private final NotificationDataRepository notificationDataRepository = mock(NotificationDataRepository.class);
    private final NotificationServiceImpl notificationService = new NotificationServiceImpl(notificationDataRepository);


    @Test
    public void testSaveNotifications_SuccessfulSave() {
        SendNotificationRequestDTO sendNotificationRequestDTO = new SendNotificationRequestDTO();
        List<AttendeeNotificationDTO> attendees = new ArrayList<>();
        AttendeeNotificationDTO attendee1 = new AttendeeNotificationDTO("Пупкин Василий Петрович", 1L);
        attendees.add(attendee1);
        sendNotificationRequestDTO.setTo(attendees);

        when(notificationDataRepository.save(any(NotificationEntity.class))).thenReturn(new NotificationEntity());

        boolean result = notificationService.saveNotifications(sendNotificationRequestDTO);

        assertTrue(result);
    }

    @Test
    public void testGetNotifications() {
        // Создаем тестовые данные
        String RqUID = "testRqUID";
        Long oldest = 123L;
        Integer pageSize = 10;
        Long employeeNumber = 456L;

        List<NotificationEntity> mockNotifications = new ArrayList<>();
        mockNotifications.add(new NotificationEntity());
        mockNotifications.add(new NotificationEntity());

        // Мокируем поведение репозитория
        when(notificationDataRepository.getByEmployeeNumber(employeeNumber, oldest)).thenReturn(mockNotifications);

        // Вызываем метод, который будем тестировать
        List<NotificationEntity> result = notificationService.getNotifications(RqUID, oldest, pageSize, employeeNumber);

        // Проверяем, что результат соответствует ожидаемым данным
        assertEquals(2, result.size());
        assertEquals("Notification 1", result.get(0).getImage());
        assertEquals("Notification 2", result.get(1).getImage());
    }

    @Test
    public void testCountUnreadNotifications() {
        Long employeeNumber = 456L;

        // Создаем список непрочитанных уведомлений для сотрудника
        List<NotificationEntity> mockUnreadNotifications = new ArrayList<>();
        mockUnreadNotifications.add(new NotificationEntity("Notification 1", false));
        mockUnreadNotifications.add(new NotificationEntity("Notification 2", false));

        // Мокируем поведение репозитория
        when(notificationDataRepository.findByEmployeeNumberAndReaded(employeeNumber, false)).thenReturn(mockUnreadNotifications);

        // Вызываем метод, который будем тестировать
        notificationService.countUnreadNotifications(employeeNumber);

        // Проверяем, что уведомления помечены как прочитанные и сохранены
        for (NotificationEntity notification : mockUnreadNotifications) {
            verify(notificationDataRepository).save(notification);
            assert(notification.isReaded()); // Проверка, что уведомление помечено как прочитанное
        }
    }

    @Test
    public void testNotificationHasRead() {
        Long notificationId = 123L;

        // Создаем мок объект уведомления
        NotificationEntity mockNotification = new NotificationEntity("Test Notification", false);

        // Мокируем поведение репозитория для findById
        when(notificationDataRepository.findById(notificationId)).thenReturn(Optional.of(mockNotification));

        // Вызываем метод, который будем тестировать
        notificationService.notificationHasRead(notificationId);

        // Проверяем, что уведомление помечено как прочитанное и сохранено
        verify(notificationDataRepository).save(mockNotification);
        assert(mockNotification.isReaded()); // Проверка, что уведомление помечено как прочитанное
    }
}
