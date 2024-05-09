package ru.project.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.notification.dto.SendNotificationRequestDTO;
import ru.project.notification.repository.NotificationDataRepository;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationDataRepository notificationDataRepository;

    @PostMapping("/")
    public String receiveAndSave (@RequestBody SendNotificationRequestDTO sendNotificationRequestDTO) {
        //save  в цикле!!!
        notificationDataRepository.save(sendNotificationRequestDTO);
    }

    @GetMapping("/") //Приходят 3 параметра oldest (Идентификатор уведомления, старше (меньше) котрого необходимо найти и вернуть уведомления),
    // pageSize (максимальное количество возвразаемых уведомлений), employeeNumber (Табельный номер сотрудника) и возвращаем уведомления в сооствтетствии с этими параметрами
    public
            //notificationDataRepository.findBy ???


    @PutMapping("/read/all") //Придёт Табельный номер и у всех уведомлений с false должно стать true (все прочитаны) и возвращаем ноль непрочитанных.
    public


    @PutMapping("/read") //Прочитать уведомления, дергаем по id  и становится true (прочитано)
    public



}
