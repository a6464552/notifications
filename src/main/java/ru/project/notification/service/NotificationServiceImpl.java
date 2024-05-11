package ru.project.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.notification.dto.AttendeeNotificationDTO;
import ru.project.notification.dto.SendNotificationRequestDTO;
import ru.project.notification.model.NotificationEntity;
import ru.project.notification.repository.NotificationDataRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDataRepository notificationDataRepository;

    public NotificationServiceImpl(NotificationDataRepository notificationDataRepository) {
        this.notificationDataRepository = notificationDataRepository;
    }


    @Override
    public boolean saveNotifications(SendNotificationRequestDTO sendNotificationRequestDTO) {
        if (sendNotificationRequestDTO == null || sendNotificationRequestDTO.getTo() == null) {
            return false;
        }

        for (AttendeeNotificationDTO notification : sendNotificationRequestDTO.getTo()) {
            if (notification != null) {
                NotificationEntity entity = new NotificationEntity();
                entity.setEmployeeNumber(notification.getEmployeeNumber());
                entity.setFio(notification.getFio());

                //entity.setChannel(sendNotificationRequestDTO.getChannel()); //в NotificationEntity нет
                entity.setProductId(sendNotificationRequestDTO.getProductId());
                entity.setSubject(sendNotificationRequestDTO.getSubject());
                entity.setType(sendNotificationRequestDTO.getType());
                entity.setContent(sendNotificationRequestDTO.getContent());
                //entity.setStorageTime(sendNotificationRequestDTO.getStorageTime()); //в NotificationEntity нет
                entity.setImage(sendNotificationRequestDTO.getImage());
                entity.setLink(sendNotificationRequestDTO.getLink());

                try {
                    notificationDataRepository.save(entity);
                } catch (Exception e) {
                    return false; // Вернуть false при возникновении ошибки сохранения уведомления
                }
            }
        }
        return true; // Вернуть true, если все уведомления успешно сохранены
    }

    @Override
    public List<NotificationEntity> getNotifications(String RqUID, Long oldest, Integer pageSize, Long employeeNumber) {

        return notificationDataRepository.getByEmployeeNumber(employeeNumber, oldest);
    }

    @Override
    public void countUnreadNotifications(Long employeeNumber) {
        List<NotificationEntity> notifications = notificationDataRepository.findByEmployeeNumberAndReaded(employeeNumber, false);
        for (NotificationEntity notification : notifications) {
            notification.setReaded(true); // Установка признака прочтения уведомления
            notificationDataRepository.save(notification); // Сохранение изменений
        }
    }


    @Override
    public void notificationHasRead(Long id) {
        // Прочитать уведомления, дергаем по id и ставим значение true (прочитано) и сохраняем изменение
        Optional<NotificationEntity> optionalNotification = notificationDataRepository.findById(id);
        optionalNotification.ifPresent(notification -> {
            notification.setReaded(true); // Установка признака прочтения уведомления
            notificationDataRepository.save(notification); // Сохранение изменений
        });
    }



}
