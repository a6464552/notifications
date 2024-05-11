package ru.project.notification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.notification.dto.SendNotificationRequestDTO;
import ru.project.notification.model.NotificationEntity;
import ru.project.notification.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @PostMapping("/")
    public ResponseEntity<String> receiveAndSave(@RequestBody SendNotificationRequestDTO sendNotificationRequestDTO) {
        boolean allNotificationsSaved = notificationService.saveNotifications(sendNotificationRequestDTO);

        if (allNotificationsSaved) {
            return ResponseEntity.ok("Уведомления успешно сохранены");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сохранения уведомлений");
        }
    }
    @GetMapping("/") // GetNotificationResponseDTO и для 400-503: error
    //Приходят 4 параметра: RqUID (уникальный id запроса),
    // oldest (Идентификатор уведомления, старше (меньше) котрого необходимо найти и вернуть уведомления),
    // pageSize (максимальное количество возвразаемых уведомлений),
    // employeeNumber (Табельный номер сотрудника)
    // и возвращаем уведомления в сооствтетствии с этими параметрами
    public List<NotificationEntity> getNotifications(
            @RequestParam("RqUID") String RqUID,
            @RequestParam("oldest") Long oldest,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("employeeNumber") Long employeeNumber) {
        // todo ap remove         @RequestBody GetNotificationsResponseDTO getNotificationsResponseDTO) {

        List<NotificationEntity> notifications = notificationService.getNotifications(RqUID, oldest, pageSize, employeeNumber);

        return notifications;
    }
    @PutMapping("/read/all") //Пометка о простении всех уведомлений: ReadNotificationResponseDTO и для 400-503: error
    public ResponseEntity<String> allNotificationsAsRead(
            @RequestParam("RqUID") String RqUID,
            @RequestParam("employeeNumber") Long employeeNumber) {

        notificationService.countUnreadNotifications(employeeNumber);

        return ResponseEntity.ok("У Вас нет непрочитанных уведомлений");
    }
    @PutMapping("/read")
    //Пометка о прочтении уведомления по указанному идентификатору: ReadNotificationResponseDTO и для 400-503: error
    public ResponseEntity<String> notificationsAsReadById(
            @RequestParam("RqUID") String RqUID,
            @RequestParam("id") Long id,
            @RequestParam("employeeNumber") Long employeeNumber) {

        notificationService.notificationHasRead(id);

        return ResponseEntity.ok("Уведомлений " + id + " прочитано");
    }
}
