package ru.project.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.notification.model.NotificationEntity;

@Repository
public interface NotificationDataRepository extends JpaRepository <NotificationEntity, Long> {
}
