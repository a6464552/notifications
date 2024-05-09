package ru.project.notification.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "notification_entity")
@Data
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Long employeeNumber; //Тебельный номер сотрудника
    private String productId; //Идентификатор продукта (имя продукта на англ.)
    private String subject; //Тема уведомления
    private String type; //Тип уведомления (Popup, Alert, ...)
    private String content; //Содержание уведомления
    private Integer dateCreation; //Дата создания
    private Integer dateDeletion; //Дата удаления
    private String image; //Ссылка на иконку уведомления
    private String link; //Ссылка указанная в уведомлении
    private Boolean _new; //Признак нового уведомления
    private Boolean read; //Признак прочитанного уведомления

    //Нужно сделать параметр/атрибут readed = false (для новых уведомлений) для 2х ручек!!!




}
