package ru.project.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.project.notification.model.NotificationEntity;

import java.util.List;

@Repository
public interface NotificationDataRepository extends JpaRepository <NotificationEntity, Long> {

    @Query(value = "SELECT n FROM NotificationEntity n WHERE n.employeeNumber = ?1 AND n.id < ?2 ORDER BY n.id DESC LIMIT 21")
    List<NotificationEntity> getByEmployeeNumber(Long employeeNumber, Long oldest);

    List<NotificationEntity> findByEmployeeNumberAndReaded(Long employeeNumber, boolean readed);

}
